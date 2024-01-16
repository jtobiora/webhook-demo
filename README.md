PAYMENT PROCESSING 
This simple and hypothetical payment processing application uses webhooks to send payment response after the payment is completed. The payment happens on a Hypothetical Payment Gateway.
A payment is assumed to be successful if the card details are correct, the date of expiry of the card has not yet reached and the amount to be paid is less than the account balance. Otherwise,
the payment fails. The payment response is sent to a webhook using a defined URL.
Once the payment response is received, the status of the payment is updated and a mail is fired to the user notifying them of their payment status. The application uses HTTP to communicate
the event (which is payment response) to the webhook.
The application receives payment requests on this endpoint

Method Type: POST 
URL: {base_url}/payment/process
Request: 
    {
        "amount": 500.90,
        "userId": "testuser@gmail.com",
        "cardNumber": "1234-5678-9876-8947",
        "expiryDate": "04/2025"
    }

Response: 
{
    "id": 1,
    "paymentAmount": 500.9,
    "status": "PENDING",
    "userId": "testuser@gmail.com",
    "transactionId": "6e45edaa-fd45-4c91-a39a-282ac921c586",
    "message": "Payment pending."
}
