# WalletAPI


# Wallet Management

# For Code switch to Master branch

1. Create Wallet: API which will create wallet for a user
url:http://localhost:8080/wallet
METHOD : POST
input: phone number
Validations : phone number should exist , only one wallet for a user.

2.API to transfer money from one wallet to another wallet (p2p).
url:http://localhost:8080/transaction
METHOD : POST
input:{payer_phone_number,payee_phone_number,amount}
Validations : payer and payee both should exist, payer should have sufficient balance.

3.Transaction Summary API
url:http://localhost:8080/transaction?userId=<userId>
METHOD: GET
Validations: userId should exists
Note : this api should return in a pagination way.

4.Transaction Status
url:http://localhost:8080/transaction?txnId=<txnID>
Method :GET
Validation: TransactionId should exists

Expectations:
=============
Flow Diagram in UML
Schema Design
Code with proper comment
Junit Test cases
