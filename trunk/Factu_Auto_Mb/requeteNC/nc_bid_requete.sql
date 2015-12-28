select 'échu', ELEMENT_ID, AMOUNT/100 as montant
from BILL_INVOICE_DETAIL bid
where bill_ref_no = numFact /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and bid.TYPE_CODE = 2
and bid.SUBTYPE_CODE in (5709, 5711, 5717, 5713, 5719, 5724, 15713)
union
select 'échoir', ELEMENT_ID, AMOUNT/100 as montant
from BILL_INVOICE_DETAIL bid
where bill_ref_no = numFact /* numFact= numero de la facture qui servivra de mapping dans l'application*/
and bid.TYPE_CODE = 2
and bid.SUBTYPE_CODE in (5708, 5710, 5716, 5712, 5718, 5723, 15712)
order by 1 DESC,2