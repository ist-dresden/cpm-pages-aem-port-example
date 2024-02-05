package composum.prototype.aemwcmcorereplacement.aitasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

/**
 * A parsing / creation class for markers like AIGenVersion(ourversion, inputfile1@version1, inputfile2@version2, ...) .
 */
class AIVersionMarker {

    private final String ourVersion;

    /**
     * inputfile@version1, ...
     */
    private final List<String> inputversions;

    public AIVersionMarker(String ourVersion, List<String> inputversions) {
        this.ourVersion = ourVersion;
        this.inputversions = inputversions != null ? inputversions : Collections.emptyList();
    }

    /**
     * Look for marker somewhere in the content.
     */
    @Nullable
    public static AIVersionMarker find(@Nullable String content) {
        if (content == null) {
            return null;
        }
        int start = content.indexOf("AIGenVersion(");
        if (start < 0) {
            return null;
        }
        int end = content.indexOf(")", start);
        if (end < 0) {
            return null;
        }
        String marker = content.substring(start + "AIGenVersion(".length(), end);
        String[] parts = marker.split(",");
        if (parts.length < 2) {
            return new AIVersionMarker(parts[0].trim(), Collections.emptyList());
        }
        String ourVersion = parts[0].trim();
        List<String> inputversions = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            inputversions.add(parts[i].trim());
        }
        return new AIVersionMarker(ourVersion, inputversions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOurVersion(), getInputversions());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AIVersionMarker)) return false;
        AIVersionMarker that = (AIVersionMarker) object;
        return Objects.equals(getOurVersion(), that.getOurVersion()) && Objects.equals(getInputversions(), that.getInputversions());
    }

    @Override
    public String toString() {
        return create(ourVersion, inputversions);
    }

    /**
     * Creates a marker like AIGenVersion(ourversion, inputfile1@version1, inputfile2@version2, ...) .
     */
    public static String create(String ourVersion, List<String> inputversions) {
        StringBuilder sb = new StringBuilder();
        sb.append("AIGenVersion(");
        sb.append(ourVersion);
        for (String inputversion : inputversions) {
            sb.append(", ");
            sb.append(inputversion);
        }
        sb.append(")");
        return sb.toString();
    }

    public String getOurVersion() {
        return ourVersion;
    }

    public List<String> getInputversions() {
        return inputversions;
    }
}
