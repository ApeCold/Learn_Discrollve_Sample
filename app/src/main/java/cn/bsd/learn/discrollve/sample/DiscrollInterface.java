package cn.bsd.learn.discrollve.sample;

public interface DiscrollInterface {

    /**
     * 当滑动的时候调用该方法，用来控制里面的控件执行动画
     * @param ratio
     */
    public void onDiscroll(float ratio);

    /**
     * 重置动画
     */
    public void onResetDiscroll();

}
