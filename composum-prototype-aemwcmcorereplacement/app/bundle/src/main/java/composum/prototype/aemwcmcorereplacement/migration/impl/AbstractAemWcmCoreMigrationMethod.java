package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;
import java.util.Objects;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Common methods for the migration.
 */
public abstract class AbstractAemWcmCoreMigrationMethod implements AemWcmCoreMigrationMethod {

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

    protected void renameAttribute(@Nonnull Resource resource, @Nonnull String oldName, @Nonnull String newName) {
        ModifiableValueMap properties = Objects.requireNonNull(resource.adaptTo(ModifiableValueMap.class));
        if (properties.containsKey(oldName)) {
            properties.put(newName, properties.get(oldName, ""));
            properties.remove(oldName);
        }
    }

    /**
     * Should be called from every migration.
     */
    protected void migrateCommonAttributes(@Nonnull Resource resource, @Nonnull PrintWriter log) {
        // FIXME(hps,08.01.24) migrate the common attributes . Probably id and cq:styleIdshould be migrated.
    }

}
