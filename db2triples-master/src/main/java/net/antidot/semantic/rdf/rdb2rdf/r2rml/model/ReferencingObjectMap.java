/* 
 * Copyright 2011-2013 Antidot opensource@antidot.net
 * https://github.com/antidot/db2triples
 * 
 * DB2Triples is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * DB2Triples is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/***************************************************************************
 *
 * R2RML Model : Referencing Object Map Interface
 *
 * A referencing object map allows using the subjects
 * of another triples map as the objects generated by 
 * a predicate-object map.
 * 
 ****************************************************************************/
package net.antidot.semantic.rdf.rdb2rdf.r2rml.model;

import java.util.Set;

public interface ReferencingObjectMap {
	
	
	/**
	 * A referencing object map has exactly one rr:parentTriplesMap property.
	 */
	public TriplesMap getParentTriplesMap();
	
	/**
	 * A referencing object map may have one or more rr:joinCondition 
	 * properties, whose values MUST be join conditions.
	 */
	public Set<JoinCondition> getJoinConditions();
	
	/**
	 * The effective SQL query of the logical table containing the 
	 * referencing object map.
	 */
	public String getChildQuery();
	
	/**
	 * The effective SQL query of the logical table of its parent triples map.
	 */
	public String getParentQuery();
	
	/**
	 *     If the referencing object map has no join condition:

    SELECT * FROM ({child-query}) AS tmp

    If the referencing object map has at least one join condition:

    SELECT * FROM ({child-query}) AS child,
                  ({parent-query}) AS parent
            WHERE child.{child-column1}=parent.{parent-column1}
              AND child.{child-column2}=parent.{parent-column2}
              AND ...

    where {child-query} is the referencing object map's child query,
    {parent-query} is its parent query, {child-column1} and {parent-column1}
    are the child column and parent column of its first join condition,
    and so on. The order of the join conditions is chosen arbitrarily.

	 */
	public String getJointSQLQuery();
	
	/**
	 * A object map knows in own Predicate Object container.
	 */
	public PredicateObjectMap getPredicateObjectMap();
	public void setPredicateObjectMap(PredicateObjectMap predicateObjectMap);
	

}
