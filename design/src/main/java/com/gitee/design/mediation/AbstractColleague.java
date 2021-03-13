package com.gitee.design.mediation;

/**
 * 抽象同事类
 * @author jie
 */
public abstract class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
