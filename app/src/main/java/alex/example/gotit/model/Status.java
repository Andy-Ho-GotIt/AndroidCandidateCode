package alex.example.gotit.model;

/**
 * Created by Alex on 5/12/2016.
 */
public enum Status {
    IN_PROGRESS{
        @Override
        public String toString() {
            return "IN PROGRESS";
        }
    }, EXPLAINED{
        @Override
        public String toString() {
            return "EXPLAINED";
        }
    };
}
