package qbert3D;

/**
 * Created by Elise Haram Vannes on 05.11.2017.
 */
interface Task <T>{

    void performTask(T t);                         // en abstrakt metode

    public static <T> Task<T> printContent()  // en konstruksjonsmetode
    {
        return t -> System.out.print(t + " ");        // et lambda-uttrykk
    }

    default Task<T> perform(Task<? super T> task)
    {
        return t -> { performTask(t); task.performTask(t); };
    }
}
