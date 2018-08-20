package jlrodriguez.jlmercadolibre.activity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jlrodriguez.jlmercadolibre.MercadoLibreJLApplication;
import jlrodriguez.jlmercadolibre.model.CardIssuer;
import jlrodriguez.jlmercadolibre.network.ApiService;

public class SelectBankPresenter implements SelectBankPresenterInterface {

    private SelectBankViewInterface mvi;
    private String paymentMethodId;
    private ApiService service;

    public SelectBankPresenter(SelectBankViewInterface mvi, String paymentMethodId, ApiService service) {
        this.mvi = mvi;
        this.paymentMethodId = paymentMethodId;
        this.service = service;
    }

    @Override
    public void requestBanks() {
        getObservable().subscribe(getObserver());
    }

    private Observable<List<CardIssuer>> getObservable(){
        return service
                .getBankByPaymentMethod(MercadoLibreJLApplication.getInstance().getPublicKey(), paymentMethodId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<CardIssuer>> getObserver(){
        return new DisposableObserver<List<CardIssuer>>() {

            @Override
            public void onNext(@NonNull List<CardIssuer> cardIssuerList) {
                mvi.displayBanks(cardIssuerList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mvi.displayError();
            }

            @Override
            public void onComplete() {

            }
        };
    }
}