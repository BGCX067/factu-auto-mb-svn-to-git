-- NRC sur les services d'accès
select distinct n.TRACKING_ID, ciem2.EXTERNAL_ID as Xid, ciem1.EXTERNAL_ID as Id_Contrat,
	   decode(p.ELEMENT_ID, 74348, 'THD - 1P') as Offre,
	   ciem1.ACTIVE_DATE as Date_Activation_Service, n.TYPE_ID_NRC, d.DESCRIPTION_TEXT as Libelle_Facturation, n.EFFECTIVE_DATE, n.RATE/100 as Montant
from NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, CUSTOMER_ID_EQUIP_MAP ciem1, BILL_INVOICE_DETAIL bid, CUSTOMER_ID_EQUIP_MAP ciem2, PRODUCT p
where n.TYPE_ID_NRC = ntd.TYPE_ID_NRC
and bid.BILL_REF_NO = numFact -- !! À MODIFIER !!  /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and d.LANGUAGE_CODE = 2
and d.DESCRIPTION_CODE = ntd.DESCRIPTION_CODE
and n.PARENT_SUBSCR_NO = ciem1.SUBSCR_NO
and ciem1.EXTERNAL_ID_TYPE = 11 /* MTVC - LeCABLE ! */
and bid.TYPE_CODE = 3 /* NRC */
and bid.TRACKING_ID = n.TRACKING_ID
and ciem1.SUBSCR_NO = ciem2.SUBSCR_NO
and ciem2.EXTERNAL_ID_TYPE = 1 /* NDI */
and n.PARENT_SUBSCR_NO = p.PARENT_SUBSCR_NO
and n.PARENT_SUBSCR_NO_RESETS = p.PARENT_SUBSCR_NO_RESETS
and p.ELEMENT_ID in (74348) /* Abonnements sur accès -> Offre THD 1P */
UNION
-- NRC pas sur les services d'accès 
select distinct n.TRACKING_ID, ciem2.EXTERNAL_ID as Xid, ciem1.EXTERNAL_ID as Id_Contrat,
	   decode(p.ELEMENT_ID, 74348, 'THD - 1P') as Offre,
	   ciem1.ACTIVE_DATE as Date_Activation_Service, n.TYPE_ID_NRC, d.DESCRIPTION_TEXT as Libelle_Facturation, n.EFFECTIVE_DATE, n.RATE/100 as Montant	   
from NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, CUSTOMER_ID_EQUIP_MAP ciem1, BILL_INVOICE_DETAIL bid, CUSTOMER_ID_EQUIP_MAP ciem2,
	 SERVICE s1, CUSTOMER_ID_EQUIP_MAP ciem3, CUSTOMER_ID_EQUIP_MAP ciem4, SERVICE s4, PRODUCT p
where n.TYPE_ID_NRC = ntd.TYPE_ID_NRC
and ntd.DESCRIPTION_CODE = d.DESCRIPTION_CODE
and d.LANGUAGE_CODE = 2
and n.TRACKING_ID = bid.TRACKING_ID
and bid.TYPE_CODE = 3 /* NRC */
and bid.BILL_REF_NO = numFact -- !! À MODIFIER !!  /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and n.PARENT_SUBSCR_NO = ciem1.SUBSCR_NO
and ciem1.EXTERNAL_ID_TYPE = 11 /* MTVC - LeCABLE ! */
and ciem1.SUBSCR_NO = ciem2.SUBSCR_NO
and ciem2.EXTERNAL_ID_TYPE = 1 /* NDI */
and n.PARENT_SUBSCR_NO = s1.SUBSCR_NO
and n.PARENT_SUBSCR_NO_RESETS = s1.SUBSCR_NO_RESETS
and s1.EMF_CONFIG_ID in (753) /* Ligne */
and s1.SUBSCR_NO = ciem3.SUBSCR_NO
and s1.SUBSCR_NO_RESETS = ciem3.SUBSCR_NO_RESETS
and ciem3.EXTERNAL_ID_TYPE  = 11 /* MTVC - LECABLE */
and ciem3.EXTERNAL_ID = ciem4.EXTERNAL_ID
and ciem4.SUBSCR_NO = s4.SUBSCR_NO
and ciem4.SUBSCR_NO_RESETS = s4.SUBSCR_NO_RESETS
and s4.EMF_CONFIG_ID in (752) /* Accès MTVC - LECABLE */
and s4.SUBSCR_NO = p.PARENT_SUBSCR_NO
and s4.SUBSCR_NO_RESETS = p.PARENT_SUBSCR_NO_RESETS
and p.ELEMENT_ID in (74348) /* Abonnements sur accès -> Offre THD 1P */
--and s1.SERVICE_ACTIVE_DT = ciem4.ACTIVE_DATE
-- ATTENTION : CETTE CLAUSE NE FONCTIONNE PAS QUAND L'ACCES ET LE NDI NE SONT PAS ACTIVES LE MEME JOUR !!! (12 cas sur la facture de novembre 2008)
UNION
-- NRC sur les charges de résiliation fictives à 0
SELECT
  p.tracking_id,
  ciem2.EXTERNAL_ID AS XID,
  ciem1.EXTERNAL_ID AS Id_Contrat,
  decode(p.ELEMENT_ID, 74348, 'THD - 1P') as Offre,
  ciem1.ACTIVE_DATE AS Date_Activation_Service,
  99911 AS TYPE_ID_NRC,
  'Frais de déconnexion' AS Libelle_Facturation,
  oso.complete_dt AS EFFECTIVE_DATE,
  0 as Montant
FROM PRODUCT p, CUSTOMER_ID_EQUIP_MAP ciem1, CUSTOMER_ID_EQUIP_MAP ciem2, ORD_SERVICE_ORDER oso
WHERE p.ELEMENT_ID in (74348) /* Abonnements sur accès -> Offre THD 1P */
  AND p.product_inactive_dt IS NOT NULL
  AND p.parent_subscr_no = ciem1.subscr_no
  AND ciem1.EXTERNAL_ID_TYPE = 11 /* MTVC - LECABLE */
  AND ciem1.SUBSCR_NO = ciem2.SUBSCR_NO
  AND ciem2.EXTERNAL_ID_TYPE = 1 /* NDI */
  AND p.parent_subscr_no = oso.subscr_no
  AND oso.service_order_type_id = 50 /* Service Disconnect */
  AND oso.complete_dt >= 'fromDate'	/* !! À MODIFIER : fromDate= date du premier jour du mois facturé qui servivra de mapping dans l'application */
  AND oso.complete_dt < 'toDate'	/* !! À MODIFIER : toDate= date du premier jour du mois suivant le mois facturé !! */
  AND oso.order_status_id = 80 /* Completed */
order by 3, 1
