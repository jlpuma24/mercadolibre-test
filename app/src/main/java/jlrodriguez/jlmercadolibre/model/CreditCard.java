
package jlrodriguez.jlmercadolibre.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditCard extends Issuer {

    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deferred_capture")
    @Expose
    private String deferredCapture;
    @SerializedName("settings")
    @Expose
    private List<Setting> settings = null;
    @SerializedName("additional_info_needed")
    @Expose
    private List<String> additionalInfoNeeded = null;
    @SerializedName("min_allowed_amount")
    @Expose
    private float minAllowedAmount;
    @SerializedName("max_allowed_amount")
    @Expose
    private Integer maxAllowedAmount;
    @SerializedName("accreditation_time")
    @Expose
    private Integer accreditationTime;
    @SerializedName("financial_institutions")
    @Expose
    private List<Object> financialInstitutions = null;
    @SerializedName("processing_modes")
    @Expose
    private List<String> processingModes = null;

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeferredCapture() {
        return deferredCapture;
    }

    public void setDeferredCapture(String deferredCapture) {
        this.deferredCapture = deferredCapture;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public List<String> getAdditionalInfoNeeded() {
        return additionalInfoNeeded;
    }

    public void setAdditionalInfoNeeded(List<String> additionalInfoNeeded) {
        this.additionalInfoNeeded = additionalInfoNeeded;
    }

    public float getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(float minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public Integer getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(Integer maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public Integer getAccreditationTime() {
        return accreditationTime;
    }

    public void setAccreditationTime(Integer accreditationTime) {
        this.accreditationTime = accreditationTime;
    }

    public List<Object> getFinancialInstitutions() {
        return financialInstitutions;
    }

    public void setFinancialInstitutions(List<Object> financialInstitutions) {
        this.financialInstitutions = financialInstitutions;
    }

    public List<String> getProcessingModes() {
        return processingModes;
    }

    public void setProcessingModes(List<String> processingModes) {
        this.processingModes = processingModes;
    }

}
