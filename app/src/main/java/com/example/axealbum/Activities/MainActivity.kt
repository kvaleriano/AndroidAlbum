package com.example.axealbum.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.YearAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.Dao.DbWorkerThread
import com.example.axealbum.Data.Landmark
import com.example.axealbum.Data.Memory
import com.example.axealbum.Data.Year
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDb : DataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = DataBase.getInstance(this)

        deleteAll()

        val years = ArrayList<Year>()
        val landmarks = ArrayList<Landmark>()
        val memories = ArrayList<Memory>()

        years.add(Year("2018", R.drawable.img_20180913_202537))
        years.add(Year("2019", R.drawable.img_20190329_195156))

        landmarks.add(getLandmark("Abril", R.drawable.img_20180421_200858, "2018"))
        landmarks.add(getLandmark("Mayo", R.drawable.img_20180526_190747, "2018"))
        landmarks.add(getLandmark("Junio", R.drawable.img_20180624_144401, "2018"))
        landmarks.add(getLandmark("Julio", R.drawable.img_20180728_123749, "2018"))
        landmarks.add(getLandmark("Agosto", R.drawable.img_20180807_203106, "2018"))
        landmarks.add(getLandmark("Septiembre", R.drawable.img_20180913_202537, "2018"))
        landmarks.add(getLandmark("Octubre", R.drawable.img_20181002_195438, "2018"))
        landmarks.add(getLandmark("Noviembre", R.drawable.img_20181102_135456_1, "2018"))
        landmarks.add(getLandmark("Diciembre", R.drawable.img_20181201_164605, "2018"))
        landmarks.add(getLandmark("Enero", R.drawable.img_20190101_000340, "2019"))
        landmarks.add(getLandmark("Febrero", R.drawable.img_20190214_074331, "2019"))
        landmarks.add(getLandmark("Marzo", R.drawable.img_20190303_124133_1, "2019"))
        landmarks.add(getLandmark("Abril", R.drawable.img_20190418_151338, "2019"))
        landmarks.add(getLandmark("Mayo", R.drawable.img_20180913_202537, "2019"))

        memories.add(getMemory("Primera cita :3", R.drawable.img_20180421_200858, "Que decir de nuestra primera cita :33 solo pude encontrar esta fotito :'( pero fue un momento muy lindo conocerteee <3. Recuerdo que estaba todo tímido jajajaja \uD83D\uDE48 y no hable muchito xddd, pero igual para mi fue un momento que nunca olvidaré :3", "Abril", "2018"))
        memories.add(getMemory("Primera sangrada :'(", R.drawable.img_20180422_011400, "En la nochecita cuando fui a la casa de mis amigos xddd y me salió sangre :'(. Quizás seguia emocionado de conocerte? :33 jajajaja pensaba que te había contado que sangre xdddd.", "Abril", "2018"))
        memories.add(getMemory("Esperando a mi anechi :3", R.drawable.img_20180428_170904, "Nuestra segunda cita :3. Esperando a mi anechi pensando ya en el rico pinkberry que se avecinaba xddd aunque acabo siendo un banana split igual de rico <3", "Abril", "2018"))
        memories.add(getMemory("Banana split :3", R.drawable.img_20180428_175128, "Ya con nuestro rico banana split que me invitaste <3. Nuestra segunda cita ya me solté un poco más creo jajajaja :3, recuerdo que estabamos sentaditos al centro y habían varias familias alrededor xddd y que me mencionaste que seguro a mis hermanos les gustaría ese lugar :3", "Abril", "2018"))
        memories.add(getMemory("Paseito por las rotondas xd", R.drawable.img_20180428_200014, "Después de nuestro delicioso banana split fuimos a caminar por las rotondas de por ahi :3, habían varios grupos tocando creo xdd. Esta foto es de un grupo de chica que tocaron recuerdo algo como rock o metal jajajaja, también me acuerdo que habían viejitos bailando en el grupo anterior xdddd y nos reimos un poco de eso :3 jajaja", "Abril", "2018"))
        memories.add(getMemory("Tercera cita <3", R.drawable.img_20180505_144504, "Solo pude encontrar esta fotito :'c. Nuestra tercera cita que nos encontramos en brasil con ejercito :3 y ese dia habia una marcha creo, algo relacionado con mi hijo no te metas o algo asi xdddd. Ese día fue cuando fuimos a buscar los crepes/salchipapas creo y estaba cerrado y acabamos comiendo menu :33 jajaja y habia un traficaso en sucre cuando ibamos a mi casa a que te asustes con las peliciulitas :33 no lo pienses, no lo digas xddd", "Mayo", "2018"))
        memories.add(getMemory("Cuarta cita, pinkberry :3", R.drawable.img_20180512_165458, "Por fin comimos nuestro pinkberry :33. Ese día creo fue el que me equivoqueee y me fui al ovalo guiterrez jajajaja, en vez de irme al ovalo de miraflores xdd :'c no conocia muy bien u.u. Me apuré e igual nos vimos prontito :3 y devoramos bien rico ese dia <3", "Mayo", "2018"))
        memories.add(getMemory("Caminatas :3", R.drawable.img_20180512_183528, "Después de devorar nos fuimos a caminar me acuerdo :3 caminamos hasta llegar cerca al mar y de ahi nos fuimos de freeeeeeeeeente jajajaja hasta el fondaso :3, me gustó mucho nuestra caminata :3 aunque terminamos muertos los 2 creo xddd", "Mayo", "2018"))
        memories.add(getMemory("Eligiendo nueva camisita xdd", R.drawable.img_20180514_190252, "A los pocos días nomas me pase de mi trabajo a plaza un día y me puse a buscar una nueva camisa para impresionarte :3 jajajaja <3, recuerdo que te pregunte cual te parecía mejor y me dijiste que la otra xdddd pero cuando leí tu mensaje ya había comprado :'c jajaja", "Mayo", "2018"))
        memories.add(getMemory("Piano bar <3", R.drawable.img_20180526_190747, "No encontré una fotito de nuestra cita anterior :'c, la semana pasada habíamos ido a Doña Julia :3 y nuestra caminata por Magdalena viendo a esa gente que se iba volando como parapenti xddd, y una señorita me agarro de la manito recuerdo :3 jajaja <3<3. Y este día fuimos a comer nuestro salchipapon con vinito a pino bar <3. Me encantoooo estuvo bien rico :3 y hablamos de muchas cositass, salimos tarde y nos agarramos de la manito nuevamente <3. Todo fue muy lindo ese día, me recontra encanto :33.", "Mayo", "2018"))
        memories.add(getMemory("La integración :p", R.drawable.img_20180528_184000, "El día de la primera integración :3, que le conté a un par de amigos que me gustasssss jajajaja :33 <3<3 Me emborraché regular ese día xddd pero creo que igual aguante mas que la mayoría :33", "Mayo", "2018"))
        memories.add(getMemory("Segundo día de pareja :3 <3", R.drawable.img_20180603_191555, "Un día después de que comenzamos a salir :33 después de mi confesión en la brasil xdddd :'c y de nuestro picnic y nuestra caminata larga :33 me alegra muchísimo haberme armado de valor y declararme :3, aunque quizás pude haberlo echo mejor jajajaja, pero igual, fue un momento lindo que comenzo nuestra historia <3", "Junio", "2018"))
        memories.add(getMemory("Makis en el APJ :3", R.drawable.img_20180610_143903, "Los makis que devoramos en el APJ que nos recomendaron :3 estaban ricos <3 solo ese wasabi rosadito estaba feitoooo jajaja, un día tenemos que volver a ir ehh :3", "Junio", "2018"))
        memories.add(getMemory("Mi chiquita hermosa distraida <3", R.drawable.img_20180610_143907, "Mi anechi esperando por los makis :3 <3", "Junio", "2018"))
        memories.add(getMemory("Anechi descubriendo mis curiosidades xdddd", R.drawable.img_20180620_220328, "Cuantas curiosidades de mi ya habras visto amor :33 jajajaja solo contigo soy así totalmente libre y sin restricciones :3 <3 esa fue una de las primeras seguro xdddd", "Junio", "2018"))
        memories.add(getMemory("Devorando en el mercadito :3", R.drawable.img_20180624_144401, "Cuando fuimos a comer menu al mercadito :3 más curiosidades mias xddd", "Junio", "2018"))
        memories.add(getMemory("Fotito besandonos <3", R.drawable.img_20180624_144911, "Fotito dandonos un besote :3 <3", "Junio", "2018"))
        memories.add(getMemory("Miradita sexy :*", R.drawable.img_20180701_162222, "Tengo una miradita curiosaaaaaa jajajaja :3 me encanta como me miras hermosa <3", "Julio", "2018"))
        memories.add(getMemory("Primer regalito de aniversario <3", R.drawable.img_20180702_223858, "El dunking donuts que me diste en nuestro primer aniversario :33 me encantaron las donutsss <3<3 aunque mis hermanos se comieron algunas :'c pero igual :3", "Julio", "2018"))
        memories.add(getMemory("Primer regalito de aniversario parte 2 xd <3", R.drawable.img_20180703_002035, "Mi mewwwww <3<3 mi primer peluchito que me diste amor :3 me encanto un montón <3", "Julio", "2018"))
        memories.add(getMemory("Fotito desprevenida :3", R.drawable.img_20180708_144728, "Cómo siempre yo tomándote fotitos desprevenidasss jajajaja, ya deberías estar acostumbrada :3 xdd", "Julio", "2018"))
        memories.add(getMemory("Fridays <3", R.drawable.img_20180715_185154, "Nuestra salidita a fridays :3 <3 me encanto esas hamburguesitas que comimoss :3 estaban bien ricas y con nuestros tragos :3", "Julio", "2018"))
        memories.add(getMemory("Comidita de fridays :3", R.drawable.img_20180715_185302, "Esas hamburguesitasss esas papitassss esos traguitosss :33 un dia volvemos chi? <3", "Julio", "2018"))
        memories.add(getMemory("El concierto de Flow xdd", R.drawable.img_20180718_204732, "El concierto de flow que fui con mis amigos :33 ahora que recuerdo ahi tambien fui con mi pupilo jajajaja, que aun no era mi pupilo en ese entonces xdd. Fue chevere ese dia :3 aunque volvi cansadito xdd", "Julio", "2018"))
        memories.add(getMemory("Fotito sin polito :3", R.drawable.img_20180721_232841, "Una de las primeras fotos que te mande sin polito creo jajaja :3 desde entonces cuantas fotitos comprometedoras mias te habre mandado xddd <3<3 Yo aún no tengo una fotito comprometedora de ti ehh :'cc xd", "Julio", "2018"))
        memories.add(getMemory("Camino a Mala :3", R.drawable.img_20180727_104117, "Nuestro primer viajecitoooooo <3<3<3 sale el cable con el que estaba cargando mi celu :3 jajaja, que puedo decir, la primera noche que pasamos juntosss, nuestro primer muy muy :3 aunque no terminamos xdddd, un montón de primeras veces pasamos ese viajecito :3", "Julio", "2018"))
        memories.add(getMemory("Menu cerca al hotel :3", R.drawable.img_20180727_152207, "Devorando nuestro menu cerca al hotel :3 aun recuerdo que fuimos caminando por ahí y daba mieditioooo jajajaja, tenían razon que se parece al agustino xddd", "Julio", "2018"))
        memories.add(getMemory("Camino a Azpitia", R.drawable.img_20180728_123749, "En la combi camino a Azpitia :3 Más fotitos desprevenidas para tu consumo :3 xdddd", "Julio", "2018"))
        memories.add(getMemory("Arroz con pato :'(", R.drawable.img_20180728_133754_1, "Ese arroz con pato estaba demasiado durooooo jajajajaja :'ccc no estaba como los otros arroz con patos que había comido antes :'c al final no comí casi nada xdddd :(", "Julio", "2018"))
        memories.add(getMemory("Paisaje con nuestras raspadillas :3", R.drawable.img_20180728_152053, "Una fotito al paisaje de Azpitia con nuestra raspadilla ahí atrás :3,, recuerdo el videito que me tomaste cruzando el río jajajaja :33 te daba miedito curzar amor ? :'cc xdd y que el camino era largo para llegar al río :( jajaja", "Julio", "2018"))
        memories.add(getMemory("Sorpresa :3", R.drawable.img_20180728_152550, "<3 xddddd", "Julio", "2018"))
        memories.add(getMemory("Segundo aniversario <3<3", R.drawable.img_20180802_082400, "El desayunito que me enviaste a mi casita <3 Mis papás se habien ido de viaje creo :33 me encantoooooo todito lo que estaba ahi estaba buenaso <3<3 siempre tan linda tú mi hermosa anett <3", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180803_125612, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180807_203106, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180824_082846, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180901_175627, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180902_142312, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180904_225900, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180913_202334, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180913_202537, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180915_235512, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180929_223648, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180930_012512, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181002_195438, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181007_205541, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181013_110324, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181015_175813, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181018_212252_1, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181029, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_135456_1, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_141027, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_200435, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_202912, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181103_091119, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181103_162226, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181109_210126, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_185723, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_185743, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_190139, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_194218, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_195844, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181201_164605, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181201_171015, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181202_180156, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181207_185913, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181215_132216, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181215_132337, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181229_001551, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20190101_000340, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190102_192430, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190108_205110, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190113_152651, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190130_192527, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190130_192842_2, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190208_193306, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_074331, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_074802, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_185906, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_221541, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190215_191155, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190223_192215, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190226_190454, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190226_191419, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190303_123929, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190303_124133_1, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190303_131225, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190304_093405, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190329_195156, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190330_144802_1, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190405_185314, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190416_233420, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190418_151338, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190418_151352, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190421_132743, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190423_191737, "", "Abril", "2019"))

        insertLandmarksInDb(landmarks)
        insertMemoriesInDb(memories)

        val adapter = YearAdapter(years)
        yearsRecyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 3)
        yearsRecyclerView.layoutManager = layoutManager
    }

    fun getEmoji(unicode: Int): String { return String(Character.toChars(unicode)) }

    private fun getLandmark(title: String, image: Int, year: String): Landmark {
        val landmark = Landmark()
        landmark.title = title
        landmark.image = image
        landmark.year = year
        return landmark
    }

    private fun getMemory(title: String, image: Int, comment: String, landmark: String, year: String): Memory {
        val memory = Memory()
        memory.title = title
        memory.image = image
        memory.comment = comment
        memory.landmark = landmark
        memory.year = year
        return memory
    }

    private fun deleteAll() {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()
            mDb?.memoryDao()?.deleteAll()
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertLandmarksInDb(landmarks: List<Landmark>) {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()

            for (i in 0 until landmarks.size) {
                mDb?.landmarkDao()?.insert(landmarks[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertMemoriesInDb(memories: List<Memory>) {

        val task = Runnable {
            mDb?.memoryDao()?.deleteAll()

            for (i in 0 until memories.size) {
                mDb?.memoryDao()?.insert(memories[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }
}
