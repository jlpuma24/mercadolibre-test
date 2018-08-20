package jlrodriguez.jlmercadolibre.activity;

import java.util.List;

import jlrodriguez.jlmercadolibre.model.InstallmentObject;

public interface InsertAmountViewInterface {
    void showInstallmentAlert(List<InstallmentObject> installmentObject);
    void displayError();
}
