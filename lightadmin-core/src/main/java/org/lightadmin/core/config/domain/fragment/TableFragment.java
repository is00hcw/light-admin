package org.lightadmin.core.config.domain.fragment;

import org.lightadmin.core.config.beans.parsing.ConfigurationUnitPropertyFilter;
import org.lightadmin.core.util.Pair;

import java.util.Set;

import static com.google.common.collect.Sets.newLinkedHashSet;

public class TableFragment implements Fragment {

	private Set<Pair<String, String>> columns = newLinkedHashSet();

	TableFragment() {
	}

	public void addColumn( String fieldName, String alias ) {
		columns.add( Pair.stringPair( fieldName, alias ) );
	}

	public Set<Pair<String, String>> getColumns() {
		return columns;
	}

	@Override
	public Fragment filter( final ConfigurationUnitPropertyFilter propertyFilter ) {
		TableFragment tableFragment = new TableFragment();
		for ( Pair<String, String> column : columns ) {
			if ( propertyFilter.apply( column.first ) ) {
				tableFragment.addColumn( column.first, column.second );
			}
		}
		return tableFragment;
	}
}