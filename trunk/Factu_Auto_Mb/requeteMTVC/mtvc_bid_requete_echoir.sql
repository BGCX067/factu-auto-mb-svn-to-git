select 'échoir', ELEMENT_ID, AMOUNT/100 as montant
from BILL_INVOICE_DETAIL bid 
where bill_ref_no = numFact /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and bid.TYPE_CODE = 2
and bid.SUBTYPE_CODE in (74346)
order by 2
