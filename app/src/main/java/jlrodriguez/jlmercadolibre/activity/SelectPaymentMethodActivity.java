package jlrodriguez.jlmercadolibre.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jlrodriguez.jlmercadolibre.R;
import jlrodriguez.jlmercadolibre.adapters.ImageAndNameListAdapter;
import jlrodriguez.jlmercadolibre.model.CreditCard;
import jlrodriguez.jlmercadolibre.network.ApiService;
import jlrodriguez.jlmercadolibre.network.NetworkClient;
import jlrodriguez.jlmercadolibre.util.Constants;

public class SelectPaymentMethodActivity extends AppCompatActivity implements SelectPaymentMethodViewInterface {

    SelectPaymentMethodPresenter mainPresenter;
    @BindView(R.id.rv_paymentWays)
    RecyclerView paymentWaysList;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment_method);
        ButterKnife.bind(this);
        amount = getIntent().getStringExtra(Constants.EXTRA_AMOUNT);
        setupMVP();
    }

    private void setupMVP() {
        mainPresenter = new SelectPaymentMethodPresenter(this, NetworkClient.getRetrofit().create(ApiService.class));
        mainPresenter.getCreditCards();
    }

    @Override
    public void displayCreditCards(final List<CreditCard> creditCards) {
        paymentWaysList.setLayoutManager(new LinearLayoutManager(this));
        if (creditCards != null) {
            ImageAndNameListAdapter adapter = new ImageAndNameListAdapter(creditCards, this);
            paymentWaysList.setAdapter(adapter);
            RecyclerItemClickSupport.addTo(paymentWaysList).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(SelectPaymentMethodActivity.this, SelectBankActivity.class);
                    intent.putExtra(Constants.EXTRA_AMOUNT, amount);
                    intent.putExtra(Constants.EXTRA_CREDIT_CARD_ID, creditCards.get(position).getId());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void displayError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }
}