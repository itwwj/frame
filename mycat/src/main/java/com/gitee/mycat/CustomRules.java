package com.gitee.mycat;

import io.mycat.config.model.rule.RuleAlgorithm;
import io.mycat.route.function.AbstractPartitionAlgorithm;

/**
 * @author jie
 */
public class CustomRules extends AbstractPartitionAlgorithm implements RuleAlgorithm {
    /**
     * 单组数据容量
     */
    private Long volume;
    /**
     * 单组DN节点数量
     */
    private Integer step;
    /**
     * 分片模
     */
    private Integer mod;

    @Override
    public void init() {
    }

    /**
     * @param columnValue 数据ID-桶ID
     * @return
     */
    @Override
    public Integer calculate(String columnValue) {
        if (columnValue != null) {
            String[] temp = columnValue.split("-");
            if (temp.length == 2) {
                try {
                    Long dataId = Long.valueOf(temp[0]);
                    Long burstId = Long.valueOf(temp[1]);
                    int group = (int) (dataId / volume) * step;
                    int pos = group + (int) (burstId % mod);
                    return pos;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * 范围计算
     *
     * @param beginValue
     * @param endValue
     * @return
     */
    @Override
    public Integer[] calculateRange(String beginValue, String endValue) {
        if (beginValue != null && endValue != null) {
            Integer begin = calculate(beginValue);
            Integer end = calculate(endValue);
            if (begin == null || end == null) {
                return new Integer[0];
            }
            if (end >= begin) {
                int len = end - begin + 1;
                Integer[] re = new Integer[len];
                for (int i = 0; i < len; i++) {
                    re[i] = begin + i;
                }
                return re;
            }
        }
        return new Integer[0];
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }
}
