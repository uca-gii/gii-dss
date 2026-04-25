# Diseño de Sistemas Software

## Ingeniería en Informática Universidad de Cádiz

### Ejercicio: Noticias

Un sistema de información de una empresa de servicios permite a los usuarios de su red mantenerse informados de diferentes servicios de información de noticias: nacionales, internacionales, deportes, ocio y cultura, música, etc.

Se pide:

- (a) Un usuario de este sistema puede solicitar al operador el aviso de uno o de varios de los eventos de dichos servicios independientemente, sin más que solicitarlo. Mostrar el diagrama de clases del patrón que mejor refleja esta relación entre el operador telefónico y el usuario.
- (b) Dibujar el diagrama de secuencia que muestre cómo un usuario solicita, consecutivamente, que se le avise de las noticias nacionales y de las de deportes. Mostrar, además, qué ocurre cuando el operador avisa de una noticia deportiva al usuario.
- (c) Una vez que el usuario ha solicitado el envío de uno o más tipos de noticias, hará uso de un objeto de tipo Notificación, que incluye el método avisar y cuyo cometido es el de enviar las noticias recibidas a la persona por uno o varios medios. Por ejemplo, el usuario puede solicitar que se le envíen al teléfono móvil (opción por defecto), por correo electrónico, etc. Las notificaciones (por uno o varios medios) se deben enviar de la forma más transparente posible para el emisor. Mostrar el diagrama de clases que lo hace posible.
- (d) Implementación en Java de el/los métodos necesarios para la función de avisar.