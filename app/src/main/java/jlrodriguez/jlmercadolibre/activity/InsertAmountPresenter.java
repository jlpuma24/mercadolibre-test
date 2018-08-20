package jlrodriguez.jlmercadolibre.activity;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jlrodriguez.jlmercadolibre.MercadoLibreJLApplication;
import jlrodriguez.jlmercadolibre.model.InstallmentObject;
import jlrodriguez.jlmercadolibre.network.ApiService;

public class InsertAmountPresenter implements InsertAmountPresenterInterface {

    private InsertAmountViewInterface mvi;
    private String paymentMethodId;
    private String amount;
    private String issuerId;
    private ApiService service;

    InsertAmountPresenter(InsertAmountViewInterface mvi, String paymentMethodId, String amount, String issuerId, ApiService service) {
        this.mvi = mvi;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.issuerId = issuerId;
        this.service = service;
    }

    @Override
    public void getInstallmentObject() {
        getObservable().subscribe(getObserver());
    }

    private Observable<List<InstallmentObject>> getObservable(){
        return service
                .getInstallmentsByBank(MercadoLibreJLApplication.getInstance().getPublicKey(), paymentMethodId, amount, issuerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<InstallmentObject>> getObserver(){
        return new DisposableObserver<List<InstallmentObject>>() {

            @Override
            public void onNext(@NonNull List<InstallmentObject> installmentObject) {
                mvi.showInstallmentAlert(installmentObject);
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
