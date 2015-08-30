
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>

<% 	

	Map conteMenu = (HashMap)request.getAttribute("contenido");
	String vtOpciones[][] = (String[][]) conteMenu.get("menu");

	
	String menu = "<ul id=\"xx\" class=\"xx\">";
	
	for (int i=0; i<vtOpciones.length; i++) {
		
		
		int indiceActual = i;
		String vtOpc[][] = vtOpciones;
		Pattern p = Pattern.compile("^" + vtOpc[indiceActual][0]+ ".*"); 
		boolean resp = false;
	    
		if (indiceActual+1 < vtOpc.length){
			Matcher m = p.matcher(vtOpc[indiceActual+1][0]); // get a matcher object
			if (m.find()) {
				resp = true;
			} else {
				resp = false;
			}
		}
		
		
		
		
		if (resp == true) {				
			menu += "<li>" + vtOpciones[i][1] +  "<ul>";
		} else {
			
			String rutaTemp = null;
			rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
			
			if (vtOpciones[i][2].indexOf(".do") != -1){
				menu += "<li> <a href=\"" + rutaTemp+ "/" + vtOpciones[i][2] + "?accion=nuevo\" class=\"level-1\">";	
			}else{
				if (!vtOpciones[i][2].equals("")){
					menu += "<li> <a href=\"" + rutaTemp+ "/" + vtOpciones[i][2] + ".jsf\" class=\"level-1\">";	
				}else{
					menu += "<li> <a href=\"" + rutaTemp+ "/" + "home.jsf\" class=\"level-1\">";
				}
			}					
			menu += vtOpciones[i][1] + "</a></li>";

		}
		
		if (i+1 < vtOpciones.length){
	    	 if (vtOpciones[i+1][0].length() < vtOpciones[i][0].length()){ 
	    		 
	    		 int cont = vtOpciones[i][0].split("\\.").length - vtOpciones[i+1][0].split("\\.").length ;
	    		 for (int y=0; y<cont; y++) {
	    			 menu +="</ul></li>";	 
	    		 }
	    	 }
		}else if (i+1 == vtOpciones.length){
	    	 if (vtOpciones[i][0].split("\\.").length > 1){
		    		 
	    		 int cont = vtOpciones[i][0].split("\\.").length-1 ;
	    		 for (int y=0; y<cont; y++) {
	    			 menu +="</ul></li>";	 
	    		 }
	    	 }
	     }	
		
    }
	
	menu += "<li>"; 
	menu += "<a class=\"level-1 level-3\" href=\"/sigefirrhh/logout.jsf\">&nbsp;&nbsp;Salir</a>";
	menu += "</li>";
	menu += "</ul>";
	
	
	
	menu= menu.replace("<ul id=\"xx\" class=\"xx\">", "<ul id=\"idmenu\" class=\"mnmenu\">");
	menu= menu.replace("<li>", "<li class=\"level-0 middle last\">");
	menu= menu.replace("<ul>", "<ul class=\"level-1\">");
	
	System.out.println("menu1 "+ menu);
	
	String menu2 = (String) conteMenu.get("menu2");
	menu2= menu2.replace("<ul id=\"xx\" class=\"xx\">", "<ul id=\"idmenu\" class=\"mnmenu\">");
	menu2= menu2.replace("<li>", "<li class=\"level-0 middle last\">");
	menu2= menu2.replace("<ul>", "<ul class=\"level-1\">");
	
	System.out.println("menu2 "+ menu2);
	
%>
		
	<style type="text/css" media="print">
		@media print {
		#contenedor {display:none;}
		#cintilloImagenes {display:none;}
		#barraEstatus {display:none;}
		}
	</style>

		<div id="contenedor" class="contenedor">		
				<script type="text/javascript">
					menu= '<%=menu%>';
					document.write(menu);
				</script>		
		</div>	