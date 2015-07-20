<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Error de aplicaci&oacute;n</title>
		<script language="javascript" src="/js/comun.js"></script>
	</head>
	
	<body class="app" oncontextmenu="return false;">

		<div align="center">
						
			</br>
	
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="840">
				<tbody>
										
				  	<tr>
						<td width="45" height="223" valign="top" background="../images/home_r3_c1.jpg"></td>
						<td width="750" valign="top" bgcolor="#ffffff">
							<table width="750" height="80" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="750">
										<div class="events_text" align="center">
											<div align="center">
												<h2><c:out value="${contenido.mensaje}}"/></h2>
											</div>											
										</div>
									</td>
								</tr>
							</tbody>
						  	</table>    
						</td>
						<td width="45" height="223" background="../images/home_r3_c3.jpg"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>