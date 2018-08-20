
package jlrodriguez.jlmercadolibre.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bin {

    @SerializedName("pattern")
    @Expose
    private String pattern;
    @SerializedName("installments_pattern")
    @Expose
    private String installmentsPattern;
    @SerializedName("exclusion_pattern")
    @Expose
    private String exclusionPattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getInstallmentsPattern() {
        return installmentsPattern;
    }

    public void setInstallmentsPattern(String installmentsPattern) {
        this.installmentsPattern = installmentsPattern;
    }

    public String getExclusionPattern() {
        return exclusionPattern;
    }

    public void setExclusionPattern(String exclusionPattern) {
        this.exclusionPattern = exclusionPattern;
    }

}
