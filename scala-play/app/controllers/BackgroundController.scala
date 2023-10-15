package controllers

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class BackgroundController @Inject()(
                                      implicit executionContext: ExecutionContext,
                                    ) {


}
