package com.onekhan.vault4s.api.v1

import cats.effect.Effect
import com.onekhan.vault4s.VaultClient
import com.onekhan.vault4s.api.v1.SystemBackendHealth.HealthResponse
import org.http4s.MediaType
import org.http4s.Method.GET
import org.http4s.headers.Accept
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.circe._
import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}

class SystemBackendHealth[F[_]](implicit ev: Effect[F], vc: VaultClient[F])
    extends Http4sClientDsl[F] {
  implicit val healthDecoder = jsonOf[F, HealthResponse]

  def health: F[HealthResponse] = {
    val request = GET(
      vc.address / "v1" / "sys" / "health",
      Accept(MediaType.application.json)
    )

    vc.httpClient.fetchAs[HealthResponse](request)
  }

}
object SystemBackendHealth {
  protected implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

  @ConfiguredJsonCodec
  case class HealthResponse(
      initialized: Boolean,
      `sealed`: Boolean,
      standby: Boolean,
      performanceStandby: Boolean,
      replicationPerformanceMode: String,
      replicationDrMode: String,
      serverTimeUtc: Double,
      version: String)

}
