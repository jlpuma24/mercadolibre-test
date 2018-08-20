package jlrodriguez.jlmercadolibre.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jlrodriguez.jlmercadolibre.R;
import jlrodriguez.jlmercadolibre.model.InstallmentObject;
import jlrodriguez.jlmercadolibre.model.PayerCost;
import jlrodriguez.jlmercadolibre.network.ApiService;
import jlrodriguez.jlmercadolibre.network.NetworkClient;
import jlrodriguez.jlmercadolibre.util.Constants;

public class InsertAmountActivity extends AppCompatActivity implements InsertAmountViewInterface {

    @BindView(R.id.moneyEditText)
    EasyMoneyEditText moneyEditText;
    InsertAmountPresenter presenter;
    String amount, issuerId, paymentMethodId, issuerName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_amount);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(Constants.EXTRA_FINAL_SCREEN)) {
            amount = getIntent().getStringExtra(Constants.EXTRA_AMOUNT);
            issuerId = getIntent().getStringExtra(Constants.EXTRA_ISSUER_ID);
            paymentMethodId = getIntent().getStringExtra(Constants.EXTRA_CREDIT_CARD_ID);
            issuerName = getIntent().getStringExtra(Constants.EXTRA_ISSUER_NAME);
            presenter = new InsertAmountPresenter(this, paymentMethodId, amount, issuerId, NetworkClient.getRetrofit().create(ApiService.class));
            presenter.getInstallmentObject();
        }
    }

    @OnClick(R.id.btn_next)
    public void onNextButtonClick() {
        if (Integer.parseInt(moneyEditText.getValueString()) > 0) {
            Intent intent = new Intent(this, SelectPaymentMethodActivity.class);
            intent.putExtra(Constants.EXTRA_AMOUNT, moneyEditText.getValueString());
            startActivity(intent);
        }
    }

    @Override
    public void showInstallmentAlert(List<InstallmentObject> installmentObject) {
        if (installmentObject != null && !installmentObject.isEmpty()) {
            showResumeAlert(installmentObject.get(0));
        } else {
            displayError();
        }
    }

    @Override
    public void displayError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }

    private void showResumeAlert(InstallmentObject installmentObject) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_resume);

        TextView textAmount = dialog.findViewById(R.id.tv_amount);
        textAmount.setText(String.format(getString(R.string.amount_format), amount));
        TextView cardName = dialog.findViewById(R.id.tv_issuer);
        cardName.setText(String.format(getString(R.string.credit_card_type_format), paymentMethodId));
        TextView issuerText = dialog.findViewById(R.id.tv_bank);
        issuerText.setText(String.format(getString(R.string.issuer_type_format), issuerName));
        TextView textInstallments = dialog.findViewById(R.id.tv_installments);
        textInstallments.setText(String.format(getString(R.string.installments_format), getRecommendedMessages(installmentObject.getPayerCosts())));

        dialog.findViewById(R.id.btn_close).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    public String getRecommendedMessages(List<PayerCost> payerCostList) {
        if (payerCostList != null && !payerCostList.isEmpty()) {
            StringBuilder installmentsBuilder = new StringBuilder();
            for (PayerCost payerCost : payerCostList) {
                installmentsBuilder.append(payerCost.getRecommendedMessage() + "\n ");
            }
            return installmentsBuilder.toString();
        }
        return "";
    }
}