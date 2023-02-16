import { useSelector } from 'react-redux'
import GameBR31 from './GameComponents/GameBR31'
import GameTheGameOfDeath from './GameComponents/GameTheGameOfDeath'
import GameFastClick from './GameComponents/GameFastClick'

const Game = (props) =>{
  const {client, subscribers, multiMeetingRoomSeq, gameType,
  br31MyTurnFlag, setBr31MyTurnFlag,br31Point,
  gameofdeathBody} = props

  const userSeq = useSelector((state) => state.userInfoReducer.userSeq)

  const pubGame = (gameName, body) => {
    // 연결이 안되어있을 경우
    if (!client || !client.current.connected) {
      alert('연결 상태를 확인해주세요.')
      return
    }
    client.current.publish({
      destination: `/pub/${gameName}/start`,
      body: body
      })
    }

  return (
    <div className={'game-modal'}>
      {gameType === undefined?(
          <div>
            <h1>게임을 선택하세요</h1>
            <button onClick={()=>{
                pubGame("br31", JSON.stringify({
                  multiMeetingRoomSeq: multiMeetingRoomSeq
                }))
              }
            }>베스킨라빈스31게임
            </button>
            <button onClick={()=>{
                pubGame("gameofdeath", JSON.stringify({
                  multiMeetingRoomSeq: multiMeetingRoomSeq,
                  startUserSeq: userSeq
                }))
              }
            }>더게임오브데스
            </button>
            <button onClick={()=>{
                pubGame("fastclick", JSON.stringify({
                  multiMeetingRoomSeq: multiMeetingRoomSeq
                }))
              }
            }>누가 게임의 신인가?
            </button>
          </div>
        ):(null)
      }
      {gameType === "BR31"?
      (<GameBR31
        client={client}
        multiMeetingRoomSeq={multiMeetingRoomSeq}
        br31MyTurnFlag={br31MyTurnFlag}
        setBr31MyTurnFlag={setBr31MyTurnFlag}
        br31Point={br31Point}/>
      ):(null)}
      {gameType === "GAMEOFDEATH"?(<GameTheGameOfDeath client={client} subscribers={subscribers} userSeq={userSeq} multiMeetingRoomSeq={multiMeetingRoomSeq} gameofdeathBody={gameofdeathBody} />):(null)}
      {gameType === "FASTCLICK"?(<GameFastClick client={client}/>):(null)}
    </div>
  )
}
export default Game;