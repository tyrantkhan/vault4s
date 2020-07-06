package com.onekhan.vault4s.exceptions

class VaultClientException(code: Int, reason: String)
  extends Exception(s"$code - $reason") {}