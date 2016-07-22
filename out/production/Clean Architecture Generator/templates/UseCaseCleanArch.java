#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

#parse("File Header.java")
public abstract class ${NAME}<T> {

    private Action1 onNext;
    private Action1 onError;
    private Subscriber<T> subscriber;
    
     public void execute() {
        this.onNext = null;
        this.onError = null;

        Observable.defer(() -> buildUseCase())
                //.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(printStackTrace)
                .subscribe(getSubscriber());

    }

    public void execute(Action1<T> onNextAction) {

        this.onNext = onNextAction;
        this.onError = null;

        Observable.defer(() -> buildUseCase())
                //.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(printStackTrace)
                .subscribe(getSubscriber());

    }

    public void execute(Action1<T> onNextAction, Action1<Throwable> onErrorAction) {

        this.onNext = onNextAction;
        this.onError = onErrorAction;

        Observable.defer(() -> buildUseCase())
                //.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.doOnError(printStackTrace)
                .subscribe(getSubscriber());

    }


    Action1<Throwable> printStackTrace = t -> t.printStackTrace();

    public abstract Observable buildUseCase();

    public Subscriber<T> getSubscriber() {

        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber = null;
        }

        if (subscriber == null) {
            subscriber = new Subscriber<T>() {
                @Override public void onCompleted() {
                }

                @Override public void onError(Throwable e) {
                    if (onError != null) {
                        onError.call(e);
                    }
                }

                @Override public void onNext(T t) {
                    if (onNext != null) {
                        onNext.call(t);
                    }
                }
            };
        }

        return subscriber;
    }

    public void unsubscribe() {
        if (subscriber != null) {
            try {
                subscriber.unsubscribe();
            } catch(Exception ex){
                // Can provoke IllegalStateException if you do realm.close of closed instance
                ex.printStackTrace();
            }
            subscriber = null;
        }
    }

    public void resetSubscription() {
        unsubscribe();

        if (onNext == null && onError == null) {
            execute();
        } else if (onNext != null && onError == null) {
            execute(onNext);
        } else if (onNext != null && onError != null) {
            execute(onNext, onError);
        }
    }

}
