using System;
using Link.Message.Client.content.complex;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace Link.Message.Client.content
{
    public class StringOrObjectConverter: JsonConverter
    {
        public override void WriteJson(JsonWriter writer, object value, JsonSerializer serializer)
        { 
            if (value is StringContent || value is TextMessageContent)
            {
                writer.WriteValue(value.ToString());
            }

            if (value is ComplexMessageContent)
            {
                writer.WriteValue(JsonConvert.SerializeObject(value, new JsonSerializerSettings
                {
                    NullValueHandling = NullValueHandling.Ignore,
                    ContractResolver = new CamelCasePropertyNamesContractResolver()
                }));
            }
        }

        public override object ReadJson(JsonReader reader, Type objectType, object existingValue, JsonSerializer serializer)
        {
            throw new NotImplementedException();
        }

        public override bool CanConvert(Type objectType)
        {
            return true;
        }
    }
}