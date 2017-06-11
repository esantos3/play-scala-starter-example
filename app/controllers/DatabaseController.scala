package controllers

import dao.TransactionDAO
import models.Transaction

import javax.inject.Inject

import play.api.libs.json.{JsObject, JsValue, Json, Writes}
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

class DatabaseController @Inject() (
  transactionDAO: TransactionDAO,
  controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  implicit val writesTransaction: Writes[Transaction] = new Writes[Transaction] {
    def writes(transaction: Transaction): JsObject = Json.obj(
      "id" -> transaction.id,
      "candidate" -> transaction.candidate,
      "contributor" -> transaction.contributorState,
      "contributor_occupation" -> transaction.contributorOccupation,
      "amount" -> transaction.amount,
      "date" -> transaction.date
    )
  }

  def transactions = Action {
    val transactions: Seq[Transaction] = Await.result(transactionDAO.all, Duration.Inf).take(5)
    val transactionsJson: JsValue = Json.toJson(transactions)
    Ok(transactionsJson)
  }

}