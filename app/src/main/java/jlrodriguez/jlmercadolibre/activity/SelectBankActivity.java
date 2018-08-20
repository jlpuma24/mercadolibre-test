package jlrodriguez.jlmercadolibre.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jlrodriguez.jlmercadolibre.R;
import jlrodriguez.jlmercadolibre.adapters.ImageAndNameListAdapter;
import jlrodriguez.jlmercadolibre.model.CardIssuer;
import jlrodriguez.jlmercadolibre.network.ApiService;
import jlrodriguez.jlmercadolibre.network.NetworkClient;
import jlrodriguez.jlmercadolibre.util.Constants;

public class SelectBankActivity extends AppCompatActivity implements SelectBankViewInterface {

    private String amount, paymentMethodId;
    SelectBankPresenter selectBankPresenter;
    @BindView(R.id.rv_cardIssuers)
    RecyclerView banksList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);
        ButterKnife.bind(this);
        amount = getIntent().getStringExtra(Constants.EXTRA_AMOUNT);
        paymentMethodId = getIntent().getStringExtra(Constants.EXTRA_CREDIT_CARD_ID);
        setupMVP();
    }

    private void setupMVP() {
        selectBankPresenter = new SelectBankPresenter(this, paymentMethodId, NetworkClient.getRetrofit().create(ApiService.class));
        selectBankPresenter.requestBanks();
    }

    @Override
    public void displayBanks(final List<CardIssuer> banks) {
        banksList.setLayoutManager(new LinearLayoutManager(this));
        if (banks != null && !banks.isEmpty()) {
            ImageAndNameListAdapter adapter = new ImageAndNameListAdapter(banks, this);
            banksList.setAdapter(adapter);
            RecyclerItemClickSupport.addTo(banksList).setOnItemClickListener((recyclerView, position, v) -> {
                Intent intent = new Intent(SelectBankActivity.this, InsertAmountActivity.class);
                intent.putExtra(Constants.EXTRA_AMOUNT, amount);
                intent.putExtra(Constants.EXTRA_CREDIT_CARD_ID, paymentMethodId);
                intent.putExtra(Constants.EXTRA_ISSUER_ID, banks.get(position).getId());
                intent.putExtra(Constants.EXTRA_ISSUER_NAME, banks.get(position).getName());
                intent.putExtra(Constants.EXTRA_FINAL_SCREEN, true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            });
        } else {
            Toast.makeText(
                    this, getString(R.string.no_banks), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void displayError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }
}