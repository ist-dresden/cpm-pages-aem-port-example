package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.util.Objects;

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
    protected boolean replaceResourceType(Resource resource, String oldResourceType, String newResourceType) {
        if (resource.getResourceType().equals(oldResourceType)) {
            Objects.requireNonNull(resource.adaptTo(ModifiableValueMap.class))
                    .put("sling:resourceType", newResourceType);
            return true;
        }
        return false;
    }

    protected void renameAttribute(Resource resource, String oldName, String newName) {
        ModifiableValueMap properties = Objects.requireNonNull(resource.adaptTo(ModifiableValueMap.class));
        if (properties.containsKey(oldName)) {
            properties.put(newName, properties.get(oldName, ""));
            properties.remove(oldName);
        }
    }

}
