# Diseño de Sistemas Software

## Ingeniería en Informática Universidad de Cádiz

### Ejercicio: Distribuidora

Una empresa distribuidora de productos recibe las peticiones de envíos de productos a sus clientes a través de un único tipo de formulario en papel que se procesa manualmente para generar la correspondiente orden al almacén.

El proceso actual consta de los siguientes pasos:

- 1. La empresa recibe un formulario
- 2. Un empleado procesa el formulario
- 3. Como resultado, el empleado genera un pedido
- 4. El empleado envía el pedido al almacén

La empresa desea automatizar el proceso ya que de ahora en adelante se irán incrementando los tipos de formularios posibles. La automatización del proceso debe tener en cuenta que los distintos tipos de formularios generarán distintas salidas. Hasta ahora, la respuesta que generaba el único tipo de formulario consistía en un pedido que se enviaba siempre al almacén. A partir de ahora, cada tipo de formulario podrá generar una respuesta distinta que deberá ser enviada o procesada por distintos sujetos, como puede ser el almacén, los clientes, los proveedores, los empleados de la empresa o combinaciones de ellos. Ejemplos: pedidos al almacén, ordenes de compra a distintos proveedores, ordenes de venta a clientes, avisos a clientes, avisos a proveedores, comunicaciones internas a los empleados de la empresa, etc.

Se pide:

- (a) (2 puntos) Aplicar el patrón command al dominio del problema propuesto de modo que se permita automatizar el procesamiento del formulario que genera un pedido al almacén, pero contemplando la posibilidad de añadir en el futuro nuevos tipos de formularios produciendo el menor impacto posible en el resto de la aplicación.
- (b) (2 puntos) El diagrama de secuencia que tiene lugar desde que la empresa crea el formulario que recoge los datos de un pedido al almacén (formularioPedido) hasta que termina su procesamiento en el almacen.
  - Nota importante: los datos del pedido que se han recogido a través del formulario son necesarios para la elaboración del pedido que se envía al almacén.
- (c) (1 punto) A la hora de mostrar los distintos formularios en pantalla surgen distintas necesidades, ya que algunos formularios serán más largos que otros, necesitando el uso de la barra de desplazamiento solo los largos. Además se contempla la posibilidad de resaltar algunos de ellos en función de su importancia y/o urgencia con un borde de un determinado color y/o grosor. Nótese que un informe que se quiera resaltar con un

borde puede también ser largo. Nótese también que un formulario puede que se deba resaltar por más de un motivo, lo que implicaría la superposición de varios bordes.

¿Qué patrón de diseño se podría utilizar en este caso de forma que el hecho de cómo se muestra cada formulario pasara desapercibido para el resto del código? Incluir el diseño de dicho patrón.