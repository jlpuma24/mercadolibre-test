package jlrodriguez.jlmercadolibre.activity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jlrodriguez.jlmercadolibre.MercadoLibreJLApplication;
import jlrodriguez.jlmercadolibre.model.CreditCard;
import jlrodriguez.jlmercadolibre.network.ApiService;

public class SelectPaymentMethodPresenter implements SelectPaymentMethodPresenterInterface {

    private SelectPaymentMethodViewInterface mvi;
    private ApiService service;

    SelectPaymentMethodPresenter(SelectPaymentMethodViewInterface mvi, ApiService service) {
        this.mvi = mvi;
        this.service = service;
    }

    @Override
    public void getCreditCards() {
        getObservable().subscribe(getObserver());
    }

    private Observable<List<CreditCard>> getObservable(){
        return service
                .getPaymentMethods(MercadoLibreJLApplication.getInstance().getPublicKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<CreditCard>> getObserver(){
        return new DisposableObserver<List<CreditCard>>() {

            @Override
            public void onNext(@NonNull List<CreditCard> creditCards) {
                mvi.displayCreditCards(creditCards);
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
