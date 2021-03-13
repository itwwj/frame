package com.gitee.design.command;

/**
 * @author jie
 */
public class DeletePageCommand extends Command {
    @Override
    public void execute() {
        super.pg.find();
        super.pg.delete();
        super.rg.delete();
        super.cg.delete();
        super.pg.plan();
    }
}
