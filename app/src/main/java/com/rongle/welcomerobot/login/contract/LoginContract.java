package com.rongle.welcomerobot.login.contract;

import com.rongle.framework.mvp.BasePresenter;
import com.rongle.framework.mvp.IBaseView;

/**
 * Created by Administrator on 2017/11/3.
 */

public interface LoginContract {
    interface LoginView extends IBaseView{

    }

    abstract class Presenter extends BasePresenter<LoginView>{

    }
}
