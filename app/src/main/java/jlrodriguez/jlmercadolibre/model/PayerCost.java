
package jlrodriguez.jlmercadolibre.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayerCost {

    @SerializedName("installments")
    @Expose
    private long installments;
    @SerializedName("installment_rate")
    @Expose
    private double installmentRate;
    @SerializedName("discount_rate")
    @Expose
    private long discountRate;
    @SerializedName("labels")
    @Expose
    private List<String> labels = null;
    @SerializedName("installment_rate_collector")
    @Expose
    private List<String> installmentRateCollector = null;
    @SerializedName("min_allowed_amount")
    @Expose
    private long minAllowedAmount;
    @SerializedName("max_allowed_amount")
    @Expose
    private long maxAllowedAmount;
    @SerializedName("recommended_message")
    @Expose
    private String recommendedMessage;
    @SerializedName("installment_amount")
    @Expose
    private double installmentAmount;
    @SerializedName("total_amount")
    @Expose
    private double totalAmount;

    public long getInstallments() {
        return installments;
    }

    public void setInstallments(long installments) {
        this.installments = installments;
    }

    public double getInstallmentRate() {
        return installmentRate;
    }

    public void setInstallmentRate(double installmentRate) {
        this.installmentRate = installmentRate;
    }

    public long getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(long discountRate) {
        this.discountRate = discountRate;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getInstallmentRateCollector() {
        return installmentRateCollector;
    }

    public void setInstallmentRateCollector(List<String> installmentRateCollector) {
        this.installmentRateCollector = installmentRateCollector;
    }

    public long getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(long minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public long getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(long maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
