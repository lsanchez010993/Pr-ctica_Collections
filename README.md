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
partes del programa.
En la clase textil incorporo el metodo booleano 'codigoBarrasRepetido' para comprobar si el codigo de barras de algun producto esta repetido. En caso de que este repetido este no se añade a la lista.
Incorporo en la clase supermercat la funcion 'buscarNomPerCodiBarres' que utiliza los streams y expresiones lambda para buscar el nombre de los productos por codigo de barras.
En la clase supermercat creo el metodo 'leerArchivo' para leer el archivo UpdateTextilPrices.dat. Dicho metodo guarda en un hashMap el contenido del archivo. Luego utiliza una funcion para comprobar si el codigo de barras del producto coincide con el codigo de barras del producto guardado en archivo .dat. 

### Comentario Alexandru Amariei
