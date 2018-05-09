package server

sealed trait Ast
case class Select(
  target: TargetClause,
  from: FromClause,
  where: WhereClause = WhereClause(Set.empty),
  groupByClause: GroupByClause = GroupByClause(Set.empty),
  havingClause: HavingClause = HavingClause(Set.empty),
  sortClause: SortClause = SortClause(Set.empty),
  limitClause: LimitClause = LimitClause()
) extends Ast

sealed trait SqlValue
case class Value(name: String) extends SqlValue
case class Expression(function: SqlFunction, value: Value) extends SqlValue

trait SqlFunction

case class TargetClause(columns: Set[SqlValue])

case class FromClause(relations: Set[Relation])
case class Relation()

case class WhereClause(predicates: Set[BinaryOperator])
case class BinaryOperator(left: SqlValue, right: SqlValue, operator: String)

case class GroupByClause(columns: Set[SqlValue])
case class HavingClause(columns: Set[SqlValue])

case class SortClause(columns: Set[SqlValue], isAsc: Boolean = true)

case class LimitClause(limit: Int = -1)