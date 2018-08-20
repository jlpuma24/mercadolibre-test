package jlrodriguez.jlmercadolibre;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import jlrodriguez.jlmercadolibre.activity.SelectBankPresenter;
import jlrodriguez.jlmercadolibre.activity.SelectBankViewInterface;
import jlrodriguez.jlmercadolibre.model.CardIssuer;
import jlrodriguez.jlmercadolibre.network.ApiService;


@RunWith(MockitoJUnitRunner.class)
public class MercadoLibreTest {

    @Mock
    private SelectBankViewInterface selectBankViewInterface;

    @Mock
    private ApiService apiService;

    private SelectBankPresenter selectBankPresenter;

    private String paymentMethodId = "visa";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        selectBankPresenter = new SelectBankPresenter(selectBankViewInterface, paymentMethodId, apiService);
    }

    @Test
    public void validIssuersByCard() {
        ArrayList<CardIssuer> items = new ArrayList<>();
        Mockito.when(apiService.getBankByPaymentMethod(Mockito.anyString(), Mockito.eq(paymentMethodId))).thenReturn(Observable.just(items));
        selectBankPresenter.requestBanks();
        Mockito.verify(selectBankViewInterface, Mockito.times(1)).displayBanks(items);
    }

    @Test
    public void validExceptionByCard() {
        Mockito.when(apiService.getBankByPaymentMethod(Mockito.anyString(), Mockito.eq(paymentMethodId))).thenReturn(Observable.error(new Throwable()));
        selectBankPresenter.requestBanks();
        Mockito.verify(selectBankViewInterface, Mockito.times(1)).displayError();
    }
}