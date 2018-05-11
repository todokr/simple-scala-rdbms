package server

import scala.collection.mutable.ArrayBuffer

case class Table(name: String, columns: Seq[Column]) {
  val data: ArrayBuffer[Tuple] = ArrayBuffer()

  def insert(value: Map[String, Any]): Unit =
    data += Tuple(columns.map(c => c -> value.get(c.name)).toMap)

  def select(columns: Seq[String]): Seq[Tuple] =
    data.map(t => Tuple(columns.map(c => Column(c) -> t.row(Column(c))).toMap))
}

case class Tuple(row: Map[Column, Option[Any]])

case class Column(name: String)