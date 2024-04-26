# Pr-ctica_Collections

Pràctica - Collections de Programació

He creado las primeras clases con los atributos requeridos y he implementado en ellas los metodos necesarios.
He creado la clase Supermercat desde donde se gestionara la aplicacion (menus y demas metodos necesarios).
En la clase Supermercat he creado un metodo booleano para comprobar si en el arrayList de productes hay menos de 100
productos. El metodo 'noSuperaNumMaxProd' lo utilizo dentro de los metodos afegir* para que haga la comprobacion
antes de añadir un nuevo producto.
En la clase Supermercat tambien he implementado un metodo que cuenta los productos repetidos, actualiza la cantidad de 
los mismos y modifica el arrayList, que luego utilizo para mostrar los productos. De este modo no hay productos repetidos.
eliminando repedidos.
Nota: La razón por la que no he utilizado HasSet para eliminar repetidos se debe a que previamente he tenido que contarlos
para saber la cantidad.
Añado una variable booleana como parametro de entrada al metodo 'contar_Y_EliminarRepetidos' para reutilizarla en otras 
partes del programa  
Creo la funcion 'buscarNomPerCodiBarres' implementando la clase stream