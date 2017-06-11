package controllers

import javax.inject.Inject
import play.api.mvc.{ AbstractController, ControllerComponents}
import dao.TransactionDAO
import models.Transaction

import scala.concurrent.ExecutionContext

class DatabaseController @Inject() (
  transactionDAO: TransactionDAO,
  controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  def fec = Action.async {
    transactionDAO.all
  }

}