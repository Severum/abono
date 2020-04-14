package fr.eni.abono.contracts;

public class SubscriptionContract {
    public static final String TABLE_NAME = "Subscriptions";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_FREQUENCY = "frequency";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";

    public static final String PRIORITY_INDISPENSABLE = "INDISPENSABLE";
    public static final String PRIORITY_IMPORTANT = "IMPORTANT";
    public static final String PRIORITY_OPTIONAL = "OPTIONAL";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "("
                + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRICE + "REAL,"
                + COLUMN_FREQUENCY + "REAL,"
                + COLUMN_NAME + "TEXT,"
                + COLUMN_DESCRIPTION + "TEXT,"
                + COLUMN_PRIORITY + "TEXT CHECK("
                    + COLUMN_PRIORITY + "IN("
                        + PRIORITY_INDISPENSABLE + ","
                        + PRIORITY_IMPORTANT + ","
                        + PRIORITY_OPTIONAL
                    + ")"
                + ")"
            + ");";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + ";";
}
