package factu_mb_param.requete;

public class RequeteDarty {

	public RequeteDarty(){
		
	}
	
	public String nrc(String numFact,String fromDate,String toDate){
	String sql="SELECT DISTINCT * FROM ( "
//NRC sur les services d'accès
+"SELECT DISTINCT n.TRACKING_ID, "
+"ciem2.EXTERNAL_ID AS XID, "
	   +"ciem1.EXTERNAL_ID AS Id_Contrat, "
	   +"DECODE(p.ELEMENT_ID, 1344, 'ZND - 2P', 1340, 'ZDP - 2P', 1341, 'ZDP - 3P', 1342, 'ZDT - 2P', 11342, 'ZDT - 2P', 1343, 'ZDT - 3P', 4300, 'ZND - 1P', 4304, 'ZDP - 1P', 4348, 'THD - 1P', 4349, 'THD - 2P', 4350, 'THD - 3P') AS Offre, "
	   +"ciem1.ACTIVE_DATE AS Date_Activation_Service, "
	   +"DECODE(n.TYPE_ID_NRC, 11382, 6382, 11387, 6387, 11385, 6385, 11390, 6390, 11386, 6386, n.TYPE_ID_NRC) AS TYPE_ID_NRC, "
	   +"DECODE(d.DESCRIPTION_TEXT, 'Frais de résiliation', 'Frais de résiliation - ADSL Nu', d.DESCRIPTION_TEXT) AS Libelle_Facturation, "
	   +"n.EFFECTIVE_DATE, "
	   +"n.RATE/100 AS Montant "
	   +"FROM NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, CUSTOMER_ID_EQUIP_MAP ciem1, BILL_INVOICE_DETAIL bid, CUSTOMER_ID_EQUIP_MAP ciem2, PRODUCT p "
+"WHERE n.TYPE_ID_NRC = ntd.TYPE_ID_NRC "
+"AND bid.BILL_REF_NO = "+numFact+" " // !! À MODIFIER !!
+"AND d.LANGUAGE_CODE = 2 "
+"AND d.DESCRIPTION_CODE = ntd.DESCRIPTION_CODE "
+"AND n.PARENT_SUBSCR_NO = ciem1.SUBSCR_NO "
+"AND ciem1.EXTERNAL_ID_TYPE = 5 "
+"AND bid.TYPE_CODE = 3 "/* NRC */
+"AND bid.TRACKING_ID = n.TRACKING_ID "
+"AND ciem1.SUBSCR_NO = ciem2.SUBSCR_NO "
+"AND ciem2.EXTERNAL_ID_TYPE = 1 "
+"AND n.PARENT_SUBSCR_NO = p.PARENT_SUBSCR_NO "
+"AND n.PARENT_SUBSCR_NO_RESETS = p.PARENT_SUBSCR_NO_RESETS "
+"AND p.ELEMENT_ID IN (1344, 1340, 1341, 1342, 11342, 1343, 4300, 4304, 4348, 4349, 4350) "/* Abonnements sur accès -> Offre 1P, 2P, 3P */
	+"UNION "
// NRC pas sur les services d'accès 
+"SELECT DISTINCT n.TRACKING_ID, "
+"ciem2.EXTERNAL_ID AS XID, "
	   +"ciem1.EXTERNAL_ID AS Id_Contrat, "
	   +"DECODE(p.ELEMENT_ID, 1344, 'ZND - 2P', 1340, 'ZDP - 2P', 1341, 'ZDP - 3P', 1342, 'ZDT - 2P', 11342, 'ZDT - 2P', 1343, 'ZDT - 3P', 4300, 'ZND - 1P', 4304, 'ZDP - 1P', 4348, 'THD - 1P', 4349, 'THD - 2P', 4350, 'THD - 3P') AS Offre, "
	   +"ciem1.ACTIVE_DATE AS Date_Activation_Service, "
	   +"DECODE(n.TYPE_ID_NRC, 11382, 6382, 11387, 6387, 11385, 6385, 11390, 6390, 11386, 6386, n.TYPE_ID_NRC) AS TYPE_ID_NRC, "
           +"DECODE(d.DESCRIPTION_TEXT, 'Frais de résiliation', 'Frais de résiliation - ADSL Nu', d.DESCRIPTION_TEXT) AS Libelle_Facturation, "
	   +"n.EFFECTIVE_DATE, "
	   +"n.RATE/100 AS Montant "
	   +"FROM NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, CUSTOMER_ID_EQUIP_MAP ciem1, BILL_INVOICE_DETAIL bid, CUSTOMER_ID_EQUIP_MAP ciem2, "
+"SERVICE s1, CUSTOMER_ID_EQUIP_MAP ciem3, CUSTOMER_ID_EQUIP_MAP ciem4, SERVICE s4, PRODUCT p "
	 +"WHERE n.TYPE_ID_NRC = ntd.TYPE_ID_NRC "
+"AND ntd.DESCRIPTION_CODE = d.DESCRIPTION_CODE "
+"AND d.LANGUAGE_CODE = 2 "
+"AND n.TRACKING_ID = bid.TRACKING_ID "
+"AND bid.TYPE_CODE = 3 "/* NRC */
+"AND bid.BILL_REF_NO = "+numFact+" " // !! À MODIFIER !!
+"AND n.PARENT_SUBSCR_NO = ciem1.SUBSCR_NO "
+"AND ciem1.EXTERNAL_ID_TYPE = 5 "/*Darty */
+"AND ciem1.SUBSCR_NO = ciem2.SUBSCR_NO "
+"AND ciem2.EXTERNAL_ID_TYPE = 1 "/* NDI */
+"AND n.PARENT_SUBSCR_NO = s1.SUBSCR_NO "
+"AND n.PARENT_SUBSCR_NO_RESETS = s1.SUBSCR_NO_RESETS "
+"AND s1.EMF_CONFIG_ID IN (33, 34, 53) "/* Opt. 5, Ligne VoIP1, THD Ligne VoIP1 */
	+"AND s1.SUBSCR_NO = ciem3.SUBSCR_NO "
+"AND s1.SUBSCR_NO_RESETS = ciem3.SUBSCR_NO_RESETS "
+"AND ciem3.EXTERNAL_ID_TYPE  = 5 "/* Darty */
+"AND ciem3.EXTERNAL_ID = ciem4.EXTERNAL_ID "
+"AND ciem4.SUBSCR_NO = s4.SUBSCR_NO "
+"AND ciem4.SUBSCR_NO_RESETS = s4.SUBSCR_NO_RESETS "
+"AND s4.EMF_CONFIG_ID IN (31, 32, 52, 67) "/* ZDP, ZDT, Accès THD */
	+"AND s4.SUBSCR_NO = p.PARENT_SUBSCR_NO "
+"AND s4.SUBSCR_NO_RESETS = p.PARENT_SUBSCR_NO_RESETS "
+"AND p.ELEMENT_ID IN (1344, 1340, 1341, 1342, 11342, 1343, 4300, 4304, 4348, 4349, 4350) "/* Abonnements sur accès -> Offre 1P, 2P, 3P */
//and s1.SERVICE_ACTIVE_DT = ciem4.ACTIVE_DATE -- ATTENTION : CETTE CLAUSE NE FONCTIONNE PAS QUAND L'ACCES ET LE NDI NE SONT PAS ACTIVES LE MEME JOUR !!! (12 cas sur la facture de novembre 2008)
+"UNION "
// NRC de rattrapage d'usage
+"SELECT DISTINCT n.TRACKING_ID, "
+"' ' AS XID, "
	   +"' ' AS Id_Contrat, "
	   +"DECODE(bid.COMPONENT_ID, 105550, 'DSL', 105551, 'DSL', 105552, 'THD') AS Offre, "
	   +"bid.TO_DATE AS Date_Activation_Service, "
	   +"bid.COMPONENT_ID AS TYPE_ID_NRC, "
	   +"DECODE(bid.COMPONENT_ID, 105550, 'Rattrapage usage Direct DSL', 105551, 'Rattrapage usage Indirect DSL', 105552, 'Rattrapage usage THD') AS Libelle_Facturation, "
	   +"n.EFFECTIVE_DATE, "
	   +"n.RATE/100 AS Montant "
	   +"FROM NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, BILL_INVOICE_DETAIL bid "
+"WHERE n.TYPE_ID_NRC = ntd.TYPE_ID_NRC "
+"AND ntd.DESCRIPTION_CODE = d.DESCRIPTION_CODE "
+"AND d.LANGUAGE_CODE = 2 "
+"AND n.TRACKING_ID = bid.TRACKING_ID "
+"AND bid.TYPE_CODE = 3 "/* NRC */
+"AND bid.BILL_REF_NO = "+numFact+" " // !! À MODIFIER !!
+"AND bid.component_id IN (105550, 105551, 105552) "
	+"UNION "
// NRC de rattrapage sur les abos Mobile+
+"SELECT DISTINCT n.TRACKING_ID, "
+"' ' AS XID, "
	   +"' ' AS Id_Contrat, "
	   +"DECODE(bid.COMPONENT_ID, 105553, 'DSL', 105554, 'THD') AS Offre, " 
	   +"bid.TO_DATE AS Date_Activation_Service, "
	   +"bid.COMPONENT_ID AS TYPE_ID_NRC, "
	   +"DECODE(bid.COMPONENT_ID, 105553, 'Rattrapage abo Darty Mobile+ DSL', 105554, 'Rattrapage abo Darty Mobile+ THD') AS Libelle_Facturation, "
	   +"n.EFFECTIVE_DATE, "
	   +"n.RATE/100 AS Montant "
	   +"FROM NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, BILL_INVOICE_DETAIL bid "
+"WHERE n.TYPE_ID_NRC = ntd.TYPE_ID_NRC "
+"AND ntd.DESCRIPTION_CODE = d.DESCRIPTION_CODE "
+"AND d.LANGUAGE_CODE = 2 "
+"AND n.TRACKING_ID = bid.TRACKING_ID "
+"AND bid.TYPE_CODE = 3 "/* NRC */
+"AND bid.BILL_REF_NO = "+numFact+" "//!! À MODIFIER !!
+"AND bid.component_id IN (105553, 105554) "
	+"UNION "
// NRC de rattrapage sur les hausses d'abonnements Option5 et DSL nu
+"SELECT DISTINCT n.TRACKING_ID, "
+"' ' AS XID, " 
	   +"' ' AS Id_Contrat, "
	   +"DECODE(bid.COMPONENT_ID, 105555, 'DSL', 105556, 'DSL', 105557, 'DSL') AS Offre, "
	   +"bid.TO_DATE AS Date_Activation_Service, "
	   +"bid.COMPONENT_ID AS TYPE_ID_NRC, "
	   +"DECODE(bid.COMPONENT_ID, 105555, 'Rattrapage abo 1P - ZND', 105556, 'Rattrapage abo 2P - ZND', 105557, 'Rattrapage abo DSL nu') AS Libelle_Facturation, "
	   +"n.EFFECTIVE_DATE, "
	   +"n.RATE/100 AS Montant "
	   +"FROM NRC n, DESCRIPTIONS d, NRC_TRANS_DESCR ntd, BILL_INVOICE_DETAIL bid "
+"WHERE n.TYPE_ID_NRC = ntd.TYPE_ID_NRC "
+"AND ntd.DESCRIPTION_CODE = d.DESCRIPTION_CODE "
+"AND d.LANGUAGE_CODE = 2 "
+"AND n.TRACKING_ID = bid.TRACKING_ID "
+"AND bid.TYPE_CODE = 3 "/* NRC */
+"AND bid.BILL_REF_NO = "+numFact+" "// !! À MODIFIER !!
+"AND bid.component_id IN (105555, 105556, 105557) "
	+"UNION "
// NRC sur les charges de résiliation fictives à 0
+"SELECT "
+"p.tracking_id, "
  +"ciem2.EXTERNAL_ID AS XID, "
  +"ciem1.EXTERNAL_ID AS Id_Contrat, "
  +"DECODE(p.ELEMENT_ID, 1344, 'ZND - 2P', 1340, 'ZDP - 2P', 1341, 'ZDP - 3P', 1342, 'ZDT - 2P', 11342, 'ZDT - 2P', 1343, 'ZDT - 3P', 4300, 'ZND - 1P', 4304, 'ZDP - 1P', 4348, 'THD - 1P', 4349, 'THD - 2P', 4350, 'THD - 3P') AS Offre, "
  +"ciem1.ACTIVE_DATE AS Date_Activation_Service, "
  +"9999 AS TYPE_ID_NRC, "
  +"'Frais de déconnexion' AS Libelle_Facturation, "
  +"oso.complete_dt AS EFFECTIVE_DATE, "
  +"0 AS Montant "
  +"FROM PRODUCT p, CUSTOMER_ID_EQUIP_MAP ciem1, CUSTOMER_ID_EQUIP_MAP ciem2, ORD_SERVICE_ORDER oso "
+"WHERE TO_NUMBER(p.element_id) IN (1344, 1340, 1341, 1342, 11342, 1343, 4300, 4304, 4348, 4349, 4350) "
  +"AND p.product_inactive_dt IS NOT NULL "
  +"AND p.parent_subscr_no = ciem1.subscr_no "
		  +"AND ciem1.EXTERNAL_ID_TYPE = 5 "/*Darty */
		  +"AND ciem1.SUBSCR_NO = ciem2.SUBSCR_NO "
		  +"AND ciem2.EXTERNAL_ID_TYPE = 1 "/* NDI */
		  +"AND p.parent_subscr_no = oso.subscr_no "
		  +"AND oso.service_order_type_id = 50 "/* Service Disconnect */
		  +"AND oso.complete_dt >= '"+fromDate+"' "	// !! À MODIFIER : premier jour du mois facturé !!
		  +"AND oso.complete_dt < '"+toDate+"' "// !! À MODIFIER : premier jour du mois suivant le mois facturé !!
  +"AND oso.order_status_id = 80 "/* Completed */
		  +") "
+"ORDER BY 3, 1";
		return sql;
	}
	
	public String usage(String numFact){
String sql="select decode(substr(TO_CHAR(cd.TYPE_ID_USG), 1, 2), 11, 'DSL direct', "
                                                     +"12, 'DSL direct', "
                                                     +"13, 'DSL indirect', "
                                                     +"14, 'DSL indirect', "
                                                     +"18, 'DSL direct', "
                                                     +"21, 'THD', "
                                                     +"22, 'THD', "
                                                     +"28, 'THD', "
                                                     +"41, 'Mobile+ DSL direct', "
                                                     +"43, 'Mobile+ DSL indirect', "
                                                     +"48, 'Mobile+ DSL direct', "
                                                     +"51, 'Mobile+ THD', "
                                                     +"58, 'Mobile+ THD') as Type_Trafic, "	
	   +"jcv.DISPLAY_VALUE as Classe_Juridiction, "
	   +"j.JURISDICTION as Code_Juridiction, "
	   +"d.DESCRIPTION_TEXT as Libellé_Juridiction, "
	   +"bid.bill_invoice_row as num_ligne_fact, "
	   +"(bid.AMOUNT + bid.RATED_AMOUNT)/100 as Euro_HT, "
	   +"(bid.SECONDARY_AMOUNT)/100 as Remise, "
	   +"(bid.AMOUNT + bid.DISCOUNT)/100 as HT_Remisé, "
	   +"bid.COMPONENT_ID as Nbre_appels, "
	   +"bid.PACKAGE_ID as Durée_en_s_ou_en_UT, "
	   +"sum(cd.SECOND_UNITS) as Durée_en_s "
+"from CDR_DATA cd, CDR_BILLED cb, JURISDICTIONS j, DESCRIPTIONS d, JURISDICTION_CLASS_VALUES jcv, BILL_INVOICE_DETAIL bid "	
+"where cd.MSG_ID = cb.MSG_ID "	
+"and cd.MSG_ID2 = cb.MSG_ID2 "	
+"and cd.MSG_ID_SERV = cb.MSG_ID_SERV "	
+"and cd.CDR_DATA_PARTITION_KEY = cb.CDR_DATA_PARTITION_KEY	"
+"and cd.SPLIT_ROW_NUM = cb.SPLIT_ROW_NUM "	
+"and cb.BILL_REF_NO = "+numFact+" "// !! À MODIFIER !!	
+"and cb.BILL_REF_RESETS = 0 "	
+"and cd.JURISDICTION = j.JURISDICTION "
+"and jcv.JURISDICTION_CLASS = j.JURISDICTION_CLASS	"
+"and jcv.LANGUAGE_CODE = 2	"
+"and j.DESCRIPTION_CODE = d.DESCRIPTION_CODE "	
+"and d.LANGUAGE_CODE = 2 "	
+"and cd.JURISDICTION = bid.RATE_TYPE "	
+"and cd.TYPE_ID_USG = bid.SUBTYPE_CODE	"
+"and bid.TYPE_CODE = 7 "/* CDRs */	
+"and cb.BILL_REF_NO = bid.BILL_REF_NO "
+"and cb.BILL_REF_RESETS = bid.BILL_REF_RESETS "	
+"and cb.BILL_INVOICE_ROW = bid.BILL_INVOICE_ROW "	
+"group by decode(substr(TO_CHAR(cd.TYPE_ID_USG), 1, 2), 11, 'DSL direct', "
                                                     +"12, 'DSL direct', "
                                                     +"13, 'DSL indirect', "
                                                     +"14, 'DSL indirect', "
                                                     +"18, 'DSL direct', "
                                                     +"21, 'THD', "
                                                     +"22, 'THD', "
                                                     +"28, 'THD', "
                                                     +"41, 'Mobile+ DSL direct', "
                                                     +"43, 'Mobile+ DSL indirect', "
                                                     +"48, 'Mobile+ DSL direct', "
                                                     +"51, 'Mobile+ THD', "
                                                     +"58, 'Mobile+ THD'), "
	   +"jcv.DISPLAY_VALUE, "
	   +"j.JURISDICTION, "
	   +"d.DESCRIPTION_TEXT, "
	   +"bid.bill_invoice_row, "
	   +"bid.AMOUNT + bid.RATED_AMOUNT, "
	   +"bid.SECONDARY_AMOUNT, "
	   +"bid.AMOUNT + bid.DISCOUNT, "
	   +"bid.COMPONENT_ID, "
	   +"bid.PACKAGE_ID "
+"order by 2, 3, 1";
return sql;
	}
	
	public String getBIDrequest(String numFact,String fromDate,String toDate){
	String sql="SELECT 'échu', ELEMENT_ID, AMOUNT/100 AS montant "
			+"FROM BILL_INVOICE_DETAIL bid "
	+"WHERE BILL_REF_NO = "+numFact+" "/*À MODIFIER */
			+"AND from_date = '"+fromDate+"' "/*À MODIFIER, voire à supprimer */
			+"AND bid.TYPE_CODE = 2 "
			+"AND bid.SUBTYPE_CODE IN (1367, 1358, 1363, 1364, 1365, 11365, 1366, 1370, 4314, 4309, 4312, 4347, 4355, 4357, 4361, 4359, 11399, 11414, 11420, 11423, 11426, 11429, 11435, 11440) "
	+"UNION "
	+"SELECT 'échoir', ELEMENT_ID, AMOUNT/100 AS montant "
	+"FROM BILL_INVOICE_DETAIL bid "
	+"WHERE BILL_REF_NO = "+numFact+" "/*À MODIFIER */
			+"AND from_date = '"+toDate+"' "/*À MODIFIER, voire à supprimer */
			+"AND bid.TYPE_CODE = 2 "
			+"AND bid.SUBTYPE_CODE IN (1359, 1357, 1353, 1354, 1355, 11355, 1356, 1362, 4313, 4308, 4311, 4346, 4354, 4356, 4360, 4358, 11398, 11413, 11419, 11422, 11425, 11428, 11434, 11439) "
	+"ORDER BY 1 DESC, 2";
			
	return sql;
	}
	
	
	public String getSubtypeCode(String numFact){
	String sql="select subtype_code, sum(amount) "
	+"from bill_invoice_detail where type_code = 2 "
	+"and BILL_REF_NO = "+numFact+" "
	+"group by  subtype_code ";
	return sql;
	}
}
