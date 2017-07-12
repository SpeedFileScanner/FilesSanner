package io.haydar.filescanner.util;

/**
 * @author wangwei on 16/5/10
 *         wangwei@jiandaola.com
 *         copy from android.util.Singleton
 */
public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create();

    public final T get() {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create();
                }

            }
        }
        return mInstance;
    }

    public final void release() {
        mInstance = null;
    }
}
