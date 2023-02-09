package org.example.metadata;

import java.util.ArrayList;
import java.util.HashMap;

public class SchemaInfo {
    private ArrayList<String> tableNames;
    private HashMap<String, ArrayList<ColumnInfo>> tableAndColumn = new HashMap<>();
}
