select decode(cd.TYPE_ID_USG, 21000, 'DTHD - Appel direct durée', 
21020, 'DTHD - Appels vers Publiphones (Direct)', 
21200, 'DTHD - Appel direct durée - Porté chez FT', 
21300, 'DTHD - Appel direct durée - Porté chez NUMERICABLE/CPTL', 
21400, 'DTHD - Appel direct durée - Porté chez autre', 
21705, 'DTHD - Appel Direct vers Porté Orange', 
21715, 'DTHD - Appel Direct vers Porté SFR', 
21725, 'DTHD - Appel Direct vers Porté Bouygues', 
21735, 'DTHD - Appel Direct vers Porté Guyane Mobile', 
21745, 'DTHD - Appel Direct vers Porté Martinique Mobile', 
21755, 'DTHD - Appel Direct vers Porté Réunion Mobile', 
22000, 'DTHD - Appel num. spéciaux impulsion', 
28000, 'DTHD - Renvoi d''appel', 
28200, 'DTHD - Renvoi d''appel - Porté chez FT', 
28300, 'DTHD - Renvoi d''appel - Porté chez NUMERICABLE/CPTL', 
28400, 'DTHD - Renvoi d''appel - Porté chez autre', 
28705, 'DTHD - Renvoi d''appel vers Porté Orange', 
28715, 'DTHD - Renvoi d''appel vers Porté SFR', 
28725, 'DTHD - Renvoi d''appel vers Porté Bouygues', 
28735, 'DTHD - Renvoi d''appel vers Porté Guyane Mobile', 
28745, 'DTHD - Renvoi d''appel vers Porté Martinique Mobile', 
28755, 'DTHD - Renvoi d''appel vers Porté Réunion Mobile',
'Autre type d''usage') as Type_Trafic,
	   jcv.DISPLAY_VALUE as Classe_Juridiction,
	   j.JURISDICTION as Code_Juridiction,
	   d.DESCRIPTION_TEXT as Libellé_Juridiction,
	   (bid.AMOUNT + bid.RATED_AMOUNT)/100 as Euro_HT,
	   (bid.SECONDARY_AMOUNT)/100 as Remise,
	   (bid.AMOUNT + bid.DISCOUNT)/100 as HT_Remisé,
	   bid.COMPONENT_ID as Nbre_appels,
	   bid.PACKAGE_ID as Durée_en_s_ou_en_UT,
	   sum(cd.SECOND_UNITS) as Durée_en_s
from CDR_DATA cd, CDR_BILLED cb, JURISDICTIONS j, DESCRIPTIONS d, JURISDICTION_CLASS_VALUES jcv, BILL_INVOICE_DETAIL bid
where cd.MSG_ID = cb.MSG_ID
and cd.MSG_ID2 = cb.MSG_ID2
and cd.MSG_ID_SERV = cb.MSG_ID_SERV
and cd.CDR_DATA_PARTITION_KEY = cb.CDR_DATA_PARTITION_KEY
and cd.SPLIT_ROW_NUM = cb.SPLIT_ROW_NUM
and cb.BILL_REF_NO = numFact -- !! À MODIFIER !!  /* numFac= numero de la facture qui servivra de mapping dans l'application*/
and cb.BILL_REF_RESETS = 0
and cd.JURISDICTION = j.JURISDICTION
and jcv.JURISDICTION_CLASS = j.JURISDICTION_CLASS
and jcv.LANGUAGE_CODE = 2
and j.DESCRIPTION_CODE = d.DESCRIPTION_CODE
and d.LANGUAGE_CODE = 2
and cd.JURISDICTION = bid.RATE_TYPE
and cd.TYPE_ID_USG = bid.SUBTYPE_CODE
and bid.TYPE_CODE = 7 /* CDRs */
and cb.BILL_REF_NO = bid.BILL_REF_NO
and cb.BILL_REF_RESETS = bid.BILL_REF_RESETS
and cb.BILL_INVOICE_ROW = bid.BILL_INVOICE_ROW
group by decode(cd.TYPE_ID_USG, 21000, 'DTHD - Appel direct durée', 
21020, 'DTHD - Appels vers Publiphones (Direct)', 
21200, 'DTHD - Appel direct durée - Porté chez FT', 
21300, 'DTHD - Appel direct durée - Porté chez NUMERICABLE/CPTL', 
21400, 'DTHD - Appel direct durée - Porté chez autre', 
21705, 'DTHD - Appel Direct vers Porté Orange', 
21715, 'DTHD - Appel Direct vers Porté SFR', 
21725, 'DTHD - Appel Direct vers Porté Bouygues', 
21735, 'DTHD - Appel Direct vers Porté Guyane Mobile', 
21745, 'DTHD - Appel Direct vers Porté Martinique Mobile', 
21755, 'DTHD - Appel Direct vers Porté Réunion Mobile', 
22000, 'DTHD - Appel num. spéciaux impulsion', 
28000, 'DTHD - Renvoi d''appel', 
28200, 'DTHD - Renvoi d''appel - Porté chez FT', 
28300, 'DTHD - Renvoi d''appel - Porté chez NUMERICABLE/CPTL', 
28400, 'DTHD - Renvoi d''appel - Porté chez autre', 
28705, 'DTHD - Renvoi d''appel vers Porté Orange', 
28715, 'DTHD - Renvoi d''appel vers Porté SFR', 
28725, 'DTHD - Renvoi d''appel vers Porté Bouygues', 
28735, 'DTHD - Renvoi d''appel vers Porté Guyane Mobile', 
28745, 'DTHD - Renvoi d''appel vers Porté Martinique Mobile', 
28755, 'DTHD - Renvoi d''appel vers Porté Réunion Mobile',
'Autre type d''usage'),
	   jcv.DISPLAY_VALUE,
	   j.JURISDICTION,
	   d.DESCRIPTION_TEXT,
	   bid.AMOUNT + bid.RATED_AMOUNT,
	   bid.SECONDARY_AMOUNT,
	   bid.AMOUNT + bid.DISCOUNT,
	   bid.COMPONENT_ID,
	   bid.PACKAGE_ID
order by 2, 3, 1