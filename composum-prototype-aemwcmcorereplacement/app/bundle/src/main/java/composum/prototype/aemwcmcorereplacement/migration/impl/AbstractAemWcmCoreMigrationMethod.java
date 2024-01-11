package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Common methods for the migration.
 */
public abstract class AbstractAemWcmCoreMigrationMethod implements AemWcmCoreMigrationMethod {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * Replaces the resource type of the given resource if it matches the old resource type.
     *
     * @return true if the resource type was replaced, false if it didn't match
     */
    protected boolean replaceResourceType(@Nonnull Resource resource, @Nonnull String oldResourceType, @Nonnull String newResourceType) {
        if (resource.getResourceType().equals(oldResourceType)) {
            Objects.requireNonNull(resource.adaptTo(ModifiableValueMap.class))
                    .put("sling:resourceType", newResourceType);
            return true;
        }
        return false;
    }

    protected void renameAttribute(@Nonnull Resource resource, @Nonnull String oldName, @Nonnull Resource targetResource, @Nonnull String newName) {
        ModifiableValueMap srcProps = Objects.requireNonNull(resource.adaptTo(ModifiableValueMap.class));
        ModifiableValueMap targetProps = Objects.requireNonNull(targetResource.adaptTo(ModifiableValueMap.class));
        if (srcProps.containsKey(oldName)) {
            targetProps.put(newName, srcProps.get(oldName, ""));
            srcProps.remove(oldName);
        }
    }

    protected void renameAttribute(@Nonnull Resource resource, @Nonnull String oldName, @Nonnull String newName) {
        renameAttribute(resource, oldName, resource, newName);
    }

    /**
     * Should be called from every migration.
     */
    protected void migrateCommonAttributes(@Nonnull Resource resource, @Nonnull PrintWriter log) {
        // FIXME(hps,08.01.24) migrate the common attributes . Probably id and cq:styleIdshould be migrated.
    }

    void unsupported(@Nonnull Resource resource, @Nonnull PrintWriter log, @Nonnull String attributeName) {
        String value = resource.getValueMap().get(attributeName, "");
        if (value != null && !value.isEmpty() && !"false".equals(value)) {
            LOG.warn("WARNING: {} not supported.", attributeName);
            log.println("WARNING: " + attributeName + " not supported.");
        }
    }

    void unsupported(@Nonnull Resource resource, @Nonnull PrintWriter log, @Nonnull String... attributeNames) {
        for (String attributeName : attributeNames) {
            unsupported(resource, log, attributeName);
        }
    }

    protected Resource getOrCreateSubresource(@Nonnull Resource resource, @Nonnull String subresourceName, String subresourceType) throws PersistenceException {
        Resource result = resource.getChild(subresourceName);
        if (result == null) {
            result = resource.getResourceResolver().create(resource, subresourceName,
                    Map.of("sling:resourceType", subresourceType));
        }
        return result;
    }

    protected boolean isTrue(@Nonnull Resource resource, @Nonnull String attributeName) {
        return "true".equals(resource.getValueMap().get(attributeName, ""));
    }

    protected void logMigrationStart(Resource resource, PrintWriter log) {
        LOG.debug("{}: Migration started for {}", this.getClass().getSimpleName(), resource.getPath());
        log.println(this.getClass().getSimpleName() + ": Migration started for " + resource.getPath());
        log.flush();
    }

    protected void logWarning(String message, Resource resource, PrintWriter log) {
        LOG.warn("{}: {}", this.getClass().getSimpleName(), message);
        log.println("WARNING: " + message + " for " + resource.getPath());
    }

    protected void logError(String message, Resource resource, PrintWriter log) {
        LOG.error("{}: {}", this.getClass().getSimpleName(), message);
        log.println("ERROR: " + message + " for " + resource.getPath());
    }

    /**
     * Rename a resource.
     */
    protected void renameNode(Resource resource, String newName) throws PersistenceException {
        // Absurd implementation since Sling doesn't support that operation. 8-{}
        // create a new node with the same properties, move all children and delete the old node.
        ResourceResolver resolver = resource.getResourceResolver();
        String newPath = resource.getParent().getPath() + "/" + newName;
        resolver.create(resource.getParent(), newName, resource.getValueMap());
        for (Resource child : resource.getChildren()) {
            resolver.move(child.getPath(), newPath);
        }
        resolver.delete(resource);
    }

}
