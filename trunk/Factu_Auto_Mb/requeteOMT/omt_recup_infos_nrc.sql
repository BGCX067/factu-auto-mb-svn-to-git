-- NRC sur les services d'accès
select distinct n.TRACKING_ID, ciem2.EXTERNAL_ID as Xid, ciem1.EXTERNAL_ID as Id_Contrat,
	   decode(p.ELEMENT_ID, 174348, 'OMT') as Offre,
	   ciem1.ACTIVE_DATE as Date_Activation_Service, n.TYPE_ID_NRC, d.DESCRIPTION_TEXT as Libelle_Facturation, n.EFFECTIVE_DATE, n.RATE/100 as Montant
from NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, CUSTOMER_ID_EQUIP_MAP ciem1, BILL_INVOICE_DETAIL bid, CUSTOMER_ID_EQUIP_MAP ciem2, PRODUCT p
where n.TYPE_ID_NRC = ntd.TYPE_ID_NRC
and bid.BILL_REF_NO = numFact -- !! À MODIFIER !! /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and d.LANGUAGE_CODE = 2
and d.DESCRIPTION_CODE = ntd.DESCRIPTION_CODE
and n.PARENT_SUBSCR_NO = ciem1.SUBSCR_NO
and ciem1.EXTERNAL_ID_TYPE = 13 /* OutreMer Telecom ! */
and bid.TYPE_CODE = 3 /* NRC */
and bid.TRACKING_ID = n.TRACKING_ID
and ciem1.SUBSCR_NO = ciem2.SUBSCR_NO
and ciem2.EXTERNAL_ID_TYPE = 1 /* NDI */
and n.PARENT_SUBSCR_NO = p.PARENT_SUBSCR_NO
and n.PARENT_SUBSCR_NO_RESETS = p.PARENT_SUBSCR_NO_RESETS
and to_number(p.ELEMENT_ID) in (174348) /* Abonnements sur accès -> Offre OMT */
UNION
-- NRC pas sur les services d'accès 
select distinct n.TRACKING_ID, ciem2.EXTERNAL_ID as Xid, ciem1.EXTERNAL_ID as Id_Contrat,
	   decode(p.ELEMENT_ID, 174348, 'OMT') as Offre,
	   ciem1.ACTIVE_DATE as Date_Activation_Service, n.TYPE_ID_NRC, d.DESCRIPTION_TEXT as Libelle_Facturation, n.EFFECTIVE_DATE, n.RATE/100 as Montant	   
from NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, CUSTOMER_ID_EQUIP_MAP ciem1, BILL_INVOICE_DETAIL bid, CUSTOMER_ID_EQUIP_MAP ciem2,
	 SERVICE s1, CUSTOMER_ID_EQUIP_MAP ciem3, CUSTOMER_ID_EQUIP_MAP ciem4, SERVICE s4, PRODUCT p
where n.TYPE_ID_NRC = ntd.TYPE_ID_NRC
and ntd.DESCRIPTION_CODE = d.DESCRIPTION_CODE
and d.LANGUAGE_CODE = 2
and n.TRACKING_ID = bid.TRACKING_ID
and bid.TYPE_CODE = 3 /* NRC */
and bid.BILL_REF_NO = numFact -- !! À MODIFIER !! /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and n.PARENT_SUBSCR_NO = ciem1.SUBSCR_NO
and ciem1.EXTERNAL_ID_TYPE = 13 /* OutreMer Telecom ! */
and ciem1.SUBSCR_NO = ciem2.SUBSCR_NO
and ciem2.EXTERNAL_ID_TYPE = 1 /* NDI */
and n.PARENT_SUBSCR_NO = s1.SUBSCR_NO
and n.PARENT_SUBSCR_NO_RESETS = s1.SUBSCR_NO_RESETS
and s1.EMF_CONFIG_ID in (1753) /* Ligne */
and s1.SUBSCR_NO = ciem3.SUBSCR_NO
and s1.SUBSCR_NO_RESETS = ciem3.SUBSCR_NO_RESETS
and ciem3.EXTERNAL_ID_TYPE  = 13 /* OutreMer Telecom ! */
and ciem3.EXTERNAL_ID = ciem4.EXTERNAL_ID
and ciem4.SUBSCR_NO = s4.SUBSCR_NO
and ciem4.SUBSCR_NO_RESETS = s4.SUBSCR_NO_RESETS
and s4.EMF_CONFIG_ID in (1752) /* Accès OMT */
and s4.SUBSCR_NO = p.PARENT_SUBSCR_NO
and s4.SUBSCR_NO_RESETS = p.PARENT_SUBSCR_NO_RESETS
and to_number(p.ELEMENT_ID) in (174348) /* Abonnements sur accès -> Offre OMT */
--and s1.SERVICE_ACTIVE_DT = ciem4.ACTIVE_DATE -- ATTENTION : CETTE CLAUSE NE FONCTIONNE PAS QUAND L'ACCES ET LE NDI NE SONT PAS ACTIVES LE 
-- MEME JOUR !!! (12 cas sur la facture Le Cable de novembre 2008)
order by 3, 1