package fr.rozanc.cocozzelle.datatable;

import lombok.Builder;

@Builder
public class DataTableMatch {

    private final boolean matches;

    public boolean matches() {
        return matches;
    }
}
