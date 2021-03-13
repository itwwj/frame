package com.gitee.design.mediation;

/**
 * 采购管理
 * @author jie
 */
public class Purchase extends AbstractColleague{
    public Purchase(AbstractMediator _mediator) {
        super(_mediator);
    }

    /**
     * 采购IBM电脑
     *
     * @param number
     */
    public void buyIBMcomputer(int number) {
     super.mediator.execute("purchase.buy", number);
    }

    /**
     * 不再采购IBM电脑
     */
    public void refuseBuyIBM() {
        System.out.println("不再采购IBM电脑");
    }
}
