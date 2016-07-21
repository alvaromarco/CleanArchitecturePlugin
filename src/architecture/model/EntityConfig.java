package architecture.model;

/**
 * Created by alvaro on 04/07/2016.
 */
public class EntityConfig {

    private String entityName;
    private boolean containsActivity;
    private boolean containsFragment;
    private boolean containsAdapter;
    private boolean containsPresenter;

    public EntityConfig(String entityName, boolean containsActivity, boolean containsFragment, boolean containsPresenter, boolean containsAdapter) {
        this.entityName = entityName;
        this.containsActivity = containsActivity;
        this.entityName = entityName;
        this.containsFragment = containsFragment;
        this.containsAdapter = containsAdapter;
        this.containsPresenter = containsPresenter;
    }

    public String getEntityName() {
        return entityName;
    }

    public boolean isContainsActivity() {
        return containsActivity;
    }

    public boolean isContainsFragment() {
        return containsFragment;
    }

    public boolean isContainsAdapter() {
        return containsAdapter;
    }

    public boolean isContainsPresenter() {
        return containsPresenter;
    }

    @Override
    public String toString() {
        return "EntityConfig{" +
                "entityName='" + entityName + '\'' +
                ", containsActivity=" + containsActivity +
                ", containsFragment=" + containsFragment +
                ", containsAdapter=" + containsAdapter +
                ", containsPresenter=" + containsPresenter +
                '}';
    }
}
