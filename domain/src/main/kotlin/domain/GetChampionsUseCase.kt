package domain

import com.javiersc.resources.resource.Resource
import domain.models.Champion
import domain.models.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChampionsUseCase @Inject constructor(private val dataDragonRepository: DataDragonRepository) {
    operator fun invoke(): Flow<Resource<List<Champion>, Error>> = dataDragonRepository.getChampions()
}
