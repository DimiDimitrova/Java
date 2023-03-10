package org.example.successpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;

public class SuccessPage extends BaseEShopPage {
    public SuccessPage(Driver driver) {
        super(driver);
    }

    public SuccessElements elements(){
        return new SuccessElements(driver);
    }

    public SuccessAssertions assertions(){
        return new SuccessAssertions(driver);
    }
}